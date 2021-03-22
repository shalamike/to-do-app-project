Coverage: 87%
# To-Do list Full Stack Web Application

One Paragraph of project description goes here
the project is a full stack web application of a to do list application 
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## installation
### Prerequisites
java14

### installation instructions without compiling jar file

in the location of the project source, enter command prompt from there and then run the following
either

./mvnw spring-boot:run

or

mvn spring-boot:run

### installation instructions to compile and run jar file

in command prompt enter the following command

java -jar target/Project-0.0.0-SNAPSHOT.java


```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

to run the tests run the following
javac -cp <junit-jar-file>;

### Unit Tests 

unit tests runs tests on individual units/snippets/ components of code. typically assessing single functionality of a class or a class method.

	@Test
	public void setInfo() {
		todo1.setInfo("do another thing");
		assertEquals(todo1.getInfo(), "do another thing");
	}


### Integration Tests 
these tests assess how those components of code work would work together in a simulated environment. therefore these tests would mimic real life
scenarios the program should run into on a regular basis as a result of user input. 

	@Test
	public void createUserTest() {
		User newUser = new User("leroy", "jenkins", "lrjking", "LEEERROOOYY@email.com", "passwordnot" );
		UserDTO expectedUserDto = userMapper.mapToDTO(newUser);
		
		UserDTO savedUser = userService.createUser(newUser);
		expectedUserDto.setUserId(savedUser.getUserId());
		
		assertThat(savedUser).isEqualTo(expectedUserDto);
	}
	
### Automation Tests 

rather than manually testing the front end/ entering inputs to a website manually, automation testing allows for the
automation of those user inputs where we can automate the simulation of user inputs

	@Test
	public void getUsersTest() {
		WebElement getUserContainer = driver.findElement(By.cssSelector("#fetchUsers"));

		Actions action = new Actions(driver);
		action.moveToElement(getUserContainer).perform();

		action.click().perform();

	}

## development board

the link to the jira board can be found below

https://team-1611740724616.atlassian.net/jira/software/projects/TDLFW/boards/4

when running integration tests, be sure to change to URL to the correct location of your local host machine 

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Michael Shalaby - shalamike**

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* i would like to thank Morgan Walsh and Edward Reynolds for their tireless support throughout the completion of this project

