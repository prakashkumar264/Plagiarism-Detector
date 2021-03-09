'''
Shagun Bhardwaj
CS 5001
Homework 3
September 27, 2018
'''

'''
Test case #1: Mechanical pencil
999993996158 - valid
999993996168 - invalid - typed a 6 instead of 5
999999396168 - invalid - switched 2 digits (3 and 9)

Test case #2: Notebook
600682781022 - valid
600682783022 - invalid - typed a 3 instead of 1
600682718022 - invalid - switched 2 digits (8 and 1)

Test case #1: Wireless mouse
097855121929 - valid
097855181929 - invalid - typed a 8 instead of 2
079855121929 - invalid - switched 2 digits (9 and 7)
'''

def main():
    
    #ask user for upc
    upc = input("Enter the UPC Code.\n")
    #reverse upc in a list
    reverse_upc = list(reversed(upc))
    #initialize num (total) to zero
    num = 0
    
    #loop through reversed upc and test if it's odd or even
    #multiply by 3 if odd, if even leave it as is; add to total num
    for i in range(len(upc)):
        if i % 2 == 1:
            num += int(reverse_upc[i]) * 3
        else:
            num += int(reverse_upc[i])

    #test if num is divisible by 10; if yes, it'a a valid upc
    #if not, it's not a valid upc
    if num % 10 == 0:
        print("Valid UPC!")
    else:
        print("Not a valid UPC.")
        
main()
