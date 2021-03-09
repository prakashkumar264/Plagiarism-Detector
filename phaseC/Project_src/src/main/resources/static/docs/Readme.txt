Plagiarism Detector Spring-Boot Web App

About:
-> This application detects the plagiarism between two C++ or Python packages. Files of different languages cannot be compared.
-> Application reports the percentage of plagiarism detected and the similar lines of code which where detected.
-> If an error or exception occurs user will be redirected to an error page. The specific error message is only displayed in the terminal and not the browser.

Instructions:
-> User selects the language of the files to be processed. Currently supported languages are C++ and Python.
-> User uploads two different packages, either an individual file or selecting multiple files from the popup.
-> Python packages can only contain .py files.
-> C++ packages can only contain .cpp or .h files.
-> The application processes the packages and checks for plagiarism and reports on the next screen.
-> The percentages are given for every possible pair of files from package 1 and package 2
-> The side-by-side line comparison is given wherever potential plagiarism is detected.
-> Percentage and similar lines of code are shown on next package.