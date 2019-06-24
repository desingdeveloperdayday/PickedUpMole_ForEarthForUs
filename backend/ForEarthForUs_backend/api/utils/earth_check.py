
MIN_EARTHLEVEL = 1
MAX_EARTHLEVEL = 8

def get_max_earthLevel():
    return MAX_EARTHLEVEL

def get_min_earthLevel():
    return MIN_EARTHLEVEL

def increase_earthLevel(earthLevel):
    if earthLevel < get_max_earthLevel():
        return earthLevel + 1
    else:
        return earthLevel
def decrease_earthLevel(earthLevel):
    if earthLevel > get_min_earthLevel():
        return earthLevel - 1
    else:
        return earthLevel