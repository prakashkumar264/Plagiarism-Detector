# This file has helper methods for the self-avoiding walk

import random

def randomPoints(xRange, yRange, howMany):
    listOfPoints = []
    for i in range(howMany):
        pt = [(random.random()*xRange - xRange/2, random.random()*yRange - yRange/2)]
        # pt = [ (int(pt[0][0]), int(pt[0][1]))]
        listOfPoints += pt
    return listOfPoints


# function to determine whether or not the intercept of 2 lines is between
# the set of points passed in.
# This is used in the self-avoiding walk to ensure that the turtle never crosses
# its own path.
# If the intercept is between points previous and current, and between points
# point1 and point2, then the line crosses its own path, and this function returns
# -1.  Otherwise, the lines do not cross, and 1 is returned to indicate the new
# point chosen is ok.
# previous --> last point in the walk
# current --> point we're checking to see if it's a valid point to walk to
# point1 --> an earlier point in the walk
# point2 --> the point after point1 in the walk
# intercept --> the point of intersection between the line joining previous and
#               current, and the line joining point1 and point2
def interceptInRange(previous, current, point1, point2, intercept):
    # need all the coordinates individually
    (previousX, previousY) = previous
    (currentX, currentY) = current
    (x1, y1) = point1
    (x2, y2) = point2
    (intX, intY) = intercept

    # there are 16 possible scenarios (outer if statements)
    # sorry about the hideous mess - i know the code is gross...
    
    if previousX < currentX and previousY < currentY and x1 < x2 and y1 < y2:
        if previousX < intX and intX < currentX and previousY < intY and intY < currentY:
            if x1 < intX and intX < x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1

    if previousX < currentX and previousY > currentY and x1 < x2 and y1 < y2:
        if previousX < intX and intX < currentX and previousY > intY and intY > currentY:
            if x1 < intX and intX < x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY < currentY and x1 < x2 and y1 < y2:
        if previousX > intX and intX > currentX and previousY < intY and intY < currentY:
            if x1 < intX and intX < x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY > currentY and x1 < x2 and y1 < y2:
        if previousX > intX and intX > currentX and previousY > intY and intY > currentY:
            if x1 < intX and intX < x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1
                
    ##

    if previousX < currentX and previousY < currentY and x1 > x2 and y1 < y2:
        if previousX < intX and intX < currentX and previousY < intY and intY < currentY:
             if x1 > intX and intX > x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1

    if previousX < currentX and previousY > currentY and x1 > x2 and y1 < y2:
        if previousX < intX and intX < currentX and previousY > intY and intY > currentY:
            if x1 > intX and intX > x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY < currentY and x1 > x2 and y1 < y2:
        if previousX > intX and intX > currentX and previousY < intY and intY < currentY:
            if x1 > intX and intX > x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY > currentY and x1 > x2 and y1 < y2:
        if previousX > intX and intX > currentX and previousY > intY and intY > currentY:
            if x1 > intX and intX > x2 and y1 < intY and intY < y2:
                return -1
        else:
            return 1

    ##

    if previousX < currentX and previousY < currentY and x1 < x2 and y1 > y2:
        if previousX < intX and intX < currentX and previousY < intY and intY < currentY:
             if x1 < intX and intX < x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

    if previousX < currentX and previousY > currentY and x1 < x2 and y1 > y2:
        if previousX < intX and intX < currentX and previousY > intY and intY > currentY:
            if x1 < intX and intX < x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY < currentY and x1 < x2 and y1 > y2:
        if previousX > intX and intX > currentX and previousY < intY and intY < currentY:
            if x1 < intX and intX < x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY > currentY and x1 < x2 and y1 > y2:
        if previousX > intX and intX > currentX and previousY > intY and intY > currentY:
            if x1 < intX and intX < x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

    ##

    if previousX < currentX and previousY < currentY and x1 > x2 and y1 > y2:
        if previousX < intX and intX < currentX and previousY < intY and intY < currentY:
             if x1 > intX and intX > x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

    if previousX < currentX and previousY > currentY and x1 > x2 and y1 > y2:
        if previousX < intX and intX < currentX and previousY > intY and intY > currentY:
            if x1 > intX and intX > x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY < currentY and x1 > x2 and y1 > y2:
        if previousX > intX and intX > currentX and previousY < intY and intY < currentY:
            if x1 > intX and intX > x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

    if previousX > currentX and previousY > currentY and x1 > x2 and y1 > y2:
        if previousX > intX and intX > currentX and previousY > intY and intY > currentY:
            if x1 > intX and intX > x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

