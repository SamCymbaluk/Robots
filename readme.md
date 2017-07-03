# Robots

Simulates present-delivering robots. Created as a solution to a Weever Apps take home exercise
View the specifications [here](https://weeverapps.github.io/interviews/robot.html) .

## Setup
Clone this repo
`git clone https://github.com/SamCymbaluk/Robots`

Install using Maven
`mvn clean install`

Execute the compiled jar the simulation parameters
`java -jar target/Robots.jar -m "<moveSequence>" -r (numberOfRobots)`

Example:
`java -jar target/Robots.jar -m "^^<^V><" -r 5`

## Usage
Once the program has been setup, there are a number of commands you can run:

* Simulate the entire move sequence
`simulate`
* Step through one move at a time
`step`
* Display the current position of the robots
`robots`
* Display the number of presents that have been delivered
`presents`
* Display the number of houses above a certain threshold of presents
`threshold <number>`
* Exit the program
`exit`

