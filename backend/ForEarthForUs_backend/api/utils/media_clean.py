import os
from django.core.files.storage import default_storage
from django.db.models import ImageField

def file_cleanup(sender, **kwargs):
    """
    File cleanup callback used to emulate the old delete
    behavior using signals. Initially django deleted linked
    files when an object containing a File/ImageField was deleted.

    Usage:
    >>> from django.db.models.signals import post_delete
    >>> post_delete.connect(file_cleanup, sender=MyModel, dispatch_uid="mymodel.file_cleanup")
    """
    for fieldname in get_readonly_fields(sender):
        field = sender._meta.get_field(fieldname)
        if field and isinstance(field, ImageField):
            inst = kwargs['instance']
            f = getattr(inst, fieldname)
            m = inst.__class__._default_manager
            if hasattr(f, 'path') and os.path.exists(f.path)\
                and not m.filter(**{'%s__exact' % fieldname: getattr(inst, fieldname)})\
                .exclude(pk=inst._get_pk_val()):
                try:
                    print("delete file:", f.path.split('/')[-1])
                    default_storage.delete(f.path)
                except:
                    pass

def get_readonly_fields(sender):
    return [f.name for f in sender._meta.get_fields()]
