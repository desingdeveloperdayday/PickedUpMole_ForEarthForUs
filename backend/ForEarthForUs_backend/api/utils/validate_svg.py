import xml.etree.cElementTree as et
from django.core.exceptions import ValidationError

def validate_svg(file):
    # Find "start" word in file and get "tag" from there
    file.seek(0)
    tag = None
    try:
        for event, el in et.iterparse(file, ('start',)):
            tag = el.tag
            break
    except et.ParseError:
        pass

    # Check that this "tag" is correct
    if tag != '{http://www.w3.org/2000/svg}svg':
        raise ValidationError('Uploaded file is not an image or SVG file.')

    # Do not forget to "reset" file
    file.seek(0)

    return file