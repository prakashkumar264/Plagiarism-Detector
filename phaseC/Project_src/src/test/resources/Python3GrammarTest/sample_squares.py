'''
Shagun Bhardwaj
CS 5001
Homework 3
September 27, 2018
'''

import turtle

def main():

    #create square variable using turtle function
    square = turtle.Turtle()
    #use speed function to draw squares faster (smaller number = faster)
    square.speed(0)

    #outside loop to draw 20 squares
    for i in range(20):
        
        #use modulo 5 to alternate between 5 different colors for square
        if i % 5 == 0:
            square.color("red")
        elif i % 5 == 1:
            square.color("yellow")
        elif i % 5 == 2:
            square.color("green")
        elif i % 5 == 3:
            square.color("orange")
        else:
            square.color("blue")
            
        #inside loop to draw each side of square
        for j in range(4):
            #each square side is size 100
            square.forward(100)
            #90 degree angles between each side of square
            square.right(90)
            
        #20 degree shift between each square
        square.right(20)
    
main()
