from rest_framework import permissions
from api.models.feed_models import Feed

class IsOwnerOrReadOnly(permissions.BasePermission):
    """
    Custom permission to only allow owners of an object to edit it.
    """

    def has_object_permission(self, request, view, obj):
        # Read permissions are allowed to any request,
        # so we'll always allow GET, HEAD or OPTIONS requests.
        if request.method in permissions.SAFE_METHODS:
            return True

        # Write permissions are only allowed to the owner of the snippet.
        return obj.owner == request.user


class IsAdminOrReadOnly(permissions.BasePermission):
    """
    Allows Write only to admin users. if not, read only.
    """

    def has_permission(self, request, view):
        # Read permissions are allowed to any request,
        # so we'll always allow GET, HEAD or OPTIONS requests.
        if request.method in permissions.SAFE_METHODS:
            return True
        
        # Write permissions are only admin user.
        return bool(request.user and request.user.is_staff)

class IsOwnerFeed(permissions.BasePermission):

    def has_permission(self, request, view):
        # can write custom code
        print(view.kwargs)
        try:
            feed = Feed.objects.get(
                pk=view.kwargs['pk'])
        except:
            return False

        if request.user.id == feed.user.id:
            return True

        return False