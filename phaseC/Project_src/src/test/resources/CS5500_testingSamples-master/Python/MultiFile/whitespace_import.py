def drawPChain(pChain, t):
    t.up()
    t.goto(pChain[0])
    t.down()

    for i in range( len( pChain)):
        t.goto( pChain[ i])


# This file has helper methods for the self-avoiding walk

# oh god this is such bad code
# don't judge me this is the first python i ever wrote
# i was only like 17
# also this is a new comment
def interceptInRange(previous, current, point1, point2, intercept):
    # need all the coordinates individually
    (previousX, previousY) = previous
    (currentX, currentY) = current
    (x1, y1) = point1
    (x2, y2) = point2
    (intX, intY) = intercept

    # sorry about the hideous mess - i know the code is gross...
    # this comment needs to stay, bc wow this code is terrible
    # but
    # here i go adding
    # some more comments
    
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
            return 1 # hello it's me, new comment






            # NEW COMMENT NEW COMMENT
    if previousX > currentX and previousY > currentY and x1 > x2 and y1 > y2:
        if previousX > intX and intX > currentX and previousY > intY and intY > currentY:
            if x1 > intX and intX > x2 and y1 > intY and intY > y2:
                return -1
        else:
            return 1

