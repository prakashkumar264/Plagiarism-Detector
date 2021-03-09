'''
Shagun Bhardwaj
CS 5001
Homework 3
September 27, 2018
'''

def main():

    #two lists with record and runs
    record = [ 'W', 'W', 'W', 'W', 'L', 'W',
                      'W', 'W', 'L', 'W', 'W', 'W',
                      'W', 'W', 'W', 'W', 'W', 'L', 
                      'L', 'L', 'W', 'W', 'L', 'L',
                      'W', 'W', 'L', 'W', 'L', 'W', 'W',
                      'W', 'L', 'L', 'W', 'L', 'W',
                      'W', 'L', 'L', 'W', 'W', 'L', 'W',
                      'W', 'W', 'W', 'L', 'W', 'W',
                      'L', 'W', 'W', 'W', 'L', 'L', 'W',
                      'W', 'W', 'W', 'L', 'L', 'W', 'L',
                      'W', 'W', 'W', 'W', 'W', 'L', 'L',
                      'L', 'W', 'L', 'W', 'W', 'L',
                      'W', 'W', 'W', 'W', 'L', 'W',
                      'L', 'W', 'W', 'W', 'W', 'W',
                      'W', 'W', 'W', 'W', 'W', 'L', 'W',
                      'W', 'W', 'W', 'L' ]

    runs = [ 4, 1, 3, 2, 7, 4, 3, 10, 8, 14, 7, 6, 7, 10,
                   3, 10, 9, 8, 7, 0, 1, 3, 4, 5, 3, 6, 4, 10,
                   6, 5, 5, 5, 6, 6, 2, 6, 5, 3, 5, 5, 5, 3, 6,
                   5, 6, 6, 5, 4, 4, 3, 6, 8, 1, 8, 8, 6, 2, 3, 5, 9, 
                   6, 7, 2, 0, 4, 2, 2, 6, 5, 2, 6, 0, 9, 2, 1, 
                   9, 14, 2, 5, 9, 9, 4, 1, 11, 1, 4, 11, 3, 10,
                   15, 7, 5, 8, 4, 6, 7, 6, 5, 1, 0 ]

    #initialize all variables to zero
    wins = 0
    losses = 0
    wins_1_run = 0
    losses_10_runs = 0
    no_runs = 0
    ten_runs = 0
    total_runs = 0
    games_played = 0

    #start a for loop that goes through entire list
    for i in range(len(record)):
        #if record for current iteration is win, add 1 on wins total
        
        if record[i] == "W":
            wins += 1
            #if win and team scored exactly  run, add 1 to wins with 1 run total
            if runs[i] == 1:
                wins_1_run += 1
                
        #if team didn't win, they lost; add 1 to losses total
        else:
            losses += 1
            #if team lost and scored 10 or more runs, add 1 to
            #losses with 10+ runs total
            if runs[i] >= 10:
                losses_10_runs += 1
                
        #if team scored 0 runs (regardless of win/loss), add 1 to no runs total
        if runs[i] == 0:
            no_runs += 1
            
        #if team scored 10 or more runs (regardless of win/loss),
        #add 1 to 10+ runs total  
        if runs[i] >= 10:
            ten_runs += 1
            
        #keep running total of runs
        total_runs += runs[i]
        
        #keep running total of games played
        games_played += 1
        
    #calculate average by dividing total runs by games played
    average_runs = total_runs / games_played
    
    #print all data
    print("Win-Loss Record: ", wins, "-", losses, sep = "")
    print("Average number of runs scored:", average_runs)
    print("Games with zero runs:", no_runs)
    print("Games with 10+ runs:", ten_runs)
    print("Games won with exactly 1 run:", wins_1_run)
    print("Games lost with 10+ runs:", losses_10_runs)
        
main()                
        
        
