from turtle import Turtle
import random
import math

# the original comment here was wrong anyway
def selfAvoidingWalk_randomDist(t, x, y, rep):
    # first 3 pWOWOWOOWOWOWOWOOWOWWOW
    
    t.up()
    t.goto(x, y)
    t.down()
    arrayOfPoints = [(x, y)] # list of points in the walk
    
    x += (t.screen.window_width() / 2) * (random.random()) * ((-1) ** random.randint(1, 2))
    y += (t.screen.window_height() / 2) * (random.random()) * ((-1) ** random.randint(1, 2))
    arrayOfPoints += [(x, y)]
    t.goto(x, y)
    (m, b) = solveLinearEquation(arrayOfPoints[0], arrayOfPoints[1]) # list of linear  all adjacent pairs of points in the walk
    linearEquations = [(m, b)]
    
    x += (t.screen.window_width() / 2) * (random.random()) * ((-1) ** random.randint(1, 2))
    y += (t.screen.window_height() / 2) * (random.random()) * ((-1) ** random.randint(1, 2))
    arrayOfPoints += [(x, y)]
    t.goto(x, y) # new comment
    (m, b) = solveLinearEquation(arrayOfPoints[1], arrayOfPoints[2])
    linearEquations += [(m, b)]
    
   




    for index in range(rep - 2): # index == 0 when len(arrayOfPoints) == 3.  So, last element is arrayOfPoints[index + 2]
        done = False
        stuck = 0
        while not done:
            



            w = (t.screen.window_width() / 2) * (random.random()) * ((-1) ** random.randint(1, 2))
            z = (t.screen.window_height() / 2) * (random.random()) * ((-1) ** random.randint(1, 2))
            
            if abs(x + w) < t.screen.window_width() / 2 and abs(y + z) < t.screen.window_height() / 2: # check if the point is within the window 
                (currentX, currentY) = (x + w, y + z) # coordinates to test
                (previousX, previousY) = arrayOfPoints[len(arrayOfPoints) - 1] # last recorded point (before new point)
                for index in range(len(linearEquations) - 1):
                   
                    (slope, intercept) = linearEquations[index] # linear equation for the points being checked
                    
                    if previousX == currentX: # no linear equation joins these points (2 y-values for the same x-value)
                        # print("Zero division error.")
                        break
                  



                    (testSlope, testIntercept) = solveLinearEquation((previousX, previousY), (currentX, currentY)) # find the linear equation joining the points
                    if testSlope == slope: # the lines are parallel (no POI)
                        # print("Zero division error.")
                        break
                    # find the POI
                    (xi, yi) = pointOfIntersection((slope, intercept), (testSlope, testIntercept))
                    # now check where the POI is in relation to the points tested.
                    check = interceptInRange((previousX, previousY), (currentX, currentY), arrayOfPoints[index], arrayOfPoints[index + 1], (xi, yi))
                    if check == -1: # if the intercept was in range, then the point is not valid
                        stuck += 1
                        break
    
                    # if the POI is not in range, and this is the lasWOWOWOOWOWOW
                    if check == 1 and (slope, intercept) == linearEquations[len(linearEquations) - 2]: 
                        done = True
            if stuck >= 100: # don't check 100 or more points - then the walk is stuck
                break

        # if the walk hONMGGG
       



        if stuck >= 100:
            print('\nWalk stuck!  Exiting...')
            break # exit the walk

        # if the point ishruiewhriuwfiurehgwou

        x += w
        y += z
        t.goto(x, y)
        arrayOfPoints += [(x, y)]
        
        (Slope, Intercept) = solveLinearEquation(arrayOfPoints[index + 2], arrayOfPoints[index + 3])
        linearEquations += [(Slope, Intercept)]
        

# fun NEW COMMENT NEW COMMENT NEW COMMENT he 2 points to find the line between
def solveLinearEquation(pointA, pointB):
    # y = mx + b
    (ax, ay) = pointA
    (bx, by) = pointB
    m = (by - ay) / (bx - ax)
    # b = y - mx
    b = by - (m * bx)
    return (m, b)


# this used to be an explanation now it's not
def pointOfIntersection(lineA, lineB):
    # mx + b = cx + d
    # mx - cx = d - b
    # x(m - c) = d - b
    # x = (d - b) / (m - c)
    # y = mx + b
    # y = cx + d
    # At the POI, the x and y coordinates are the same
    
    (m, b) = lineA
    (c, d) = lineB
    



    xi = (d - b) / (m - c)
    yi = (m * xi) + b
    
    return (xi, yi)

def randomPoints(xRange, yRange, howMany):
    listOfPoints = []
 
    for i in range(howMany):
        pt = [(random.random()*xRange - xRange/2, random.random()*yRange - yRange/2)]
    


        listOfPoints += pt
    return listOfPoints

def drawVertices(vList, t):
    for i in range( len( vList)):
        pt = vList[i]
        t.up()
        t.goto(pt[0], pt[1])
        t.dot()

# NEW COMMENT NEW COMMENT NEW COMMENT
def randFromList(l):
    rInd = random.randint(0, len(l)-1)
    # new COMMENT
    retVal = l[rInd]
    l.remove(l[rInd])
    return (retVal, l)

def isVisible(aPt, bPt, eqList, ptList, tur):
    abEq = solveLinearEquation(aPt, bPt)
    
    for i in range( len( eqList)):
        (slope, intercept) = eqList[i]
        (abSlope, abIntercept) = abEq
        intersectionPt = pointOfIntersection( abEq, eqList[i])
        if intersectionPt == bPt or intersectionPt == aPt or intersectionPt == ptList[i] or intersectionPt == ptList[i+1]:
            continue
        if aPt == ptList[ i] or aPt == ptList[ i + 1] or bPt == ptList[ i] or bPt == ptList[ i + 1]:
            continue
        wat = interceptInRange(aPt, bPt, ptList[i], ptList[i+1], intersectionPt)
        if wat == -1:
            # tur.up()
            # there used to be some commented out code here





            return False

    return True

def makeEqList(l):
    newEqList = []
    for i in range( len( l)-1):
        newEqList += [solveLinearEquation(l[i], l[i+1])]
    return newEqList

def polyList(vList, tur):
    copyOfList = []
    for v in vList:
        copyOfList = copyOfList + [ v]


    (fst, vList) = randFromList(vList)
    (snd, vList) = randFromList(vList)
    


    (trd, vList) = randFromList(vList)

    
    pChain = [fst, snd, trd, fst]
    eqList = makeEqList( pChain)

    
    # tur.pencolor( random.randint(0, 255)/255.0, random.randint(0, 255)/255.0, random.randint(0, 255)/255.0)
    
    tur.hideturtle()
    
    #input()
    while len( vList) > 0:
    



        # tur.clear()

        #tur.pencolor( random.randint(0, 255)/255.0, random.randint(0, 255)/255.0, random.randint(0, 255)/255.0)
        
        # This is in the MultiFile directory (in the CPP directory). 
        #  a generic symbolic representation of some vector 
        # spaces in C++, with functionality to prove 8 of the basic axioms (everything except closure).
        (v, vList) = randFromList( vList)
        # want to situate vList[i] in pChain
        sitList = []
        for j in range( len( pChain)-1):
            if isVisible( v, pChain[j], eqList, pChain, tur):
                if isVisible( v, pChain[j+1], eqList, pChain, tur):
                    sitList += [j]
        if len( sitList) == 0:
            tur.up()

            tur.pencolor("black")
            tur.goto(v)
            tur.dot()
            break
        if len(sitList) == 1:
            nrInd = 0
        else:
            nrInd = random.randint(0, len(sitList)-1)
        addAt = sitList[nrInd]

        pChain.insert(addAt+1, v)



        eqList = makeEqList( pChain) # RANDOM GARBAGE COMMENT


        # TRASH TRASH

    # tur.clear()

    # drawVertices( copyOfList, tur)

    return pChain

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


# main function HELLO NEW COMMENTS
def main():
        t = Turtle()
        # promp user for number of steps in the walk
        t.pencolor("red")
        t.screen.bgcolor("white")


        l = randomPoints(t.screen.window_width()*0.9, t.screen.window_height()*0.9, 50)
        l1 = []
        l2 = []
        for v in l:
            l1 = l1 + [v]
            l2 = l2 + [v]

        for i in range( len( l)):
            print (l[i])

        drawVertices(l, t)
        pChain = polyList( l, t)
        drawPChain(pChain, t)

        # bye old comments
        # but hello new comment
        
        t.hideturtle()

        print ('\n\nProgram Done!')
        input()
        
main()
             
                
                
                
            
            
