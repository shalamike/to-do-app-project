'use strict';

// var resHistory = [];
console.log("hellooooo");

function parseUserData(data){
  console.log(data);
  console.log("now showing javascript object");
   let jsonObject = JSON.parse(JSON.stringify(data));
   let nestedArray = Object.values(jsonObject);
   let userList = nestedArray[0].userDTOList;
   //console.log(userList[0].todos[0]);

   convertUsersTotable(userList);

}

function clearData(todo){
  let i = document.getElementById(table).rows.length -1;
  for(i; i>0; i--){
      document.getElementById(table).deleteRow(i);
  }
}

function convertUsersTotable(userList){

  if(userList === undefined){
    window.confirm("no tasks present");
  }else {
    for (let i = 0; i < userList.length; i++){
      const row = document.createElement('tr');
      const cell1 = document.createElement('td');
      const cell2 = document.createElement('td');
      const cell3 = document.createElement('td');
      const cell4 = document.createElement('td');
      const cell5 = document.createElement('td');
      cell1.innerHTML = userList[i].userId;
      cell2.innerHTML = userList[i].firstName;
      cell3.innerHTML = userList[i].lastName;
      cell4.innerHTML = userList[i].userName;
      cell5.innerHTML = userList[i].email;   
      row.appendChild(cell1);
      row.appendChild(cell2);
      row.appendChild(cell3);
      row.appendChild(cell4);
      row.appendChild(cell5);
      document.getElementById("userResultSet").appendChild(row);
  
      console.log(userList[i].todos)
    }
  }
}


function parseAndConvertToUserTaskTable(data){
  console.log(data)
  if (data === null || data.todos.length === 0){
    window.confirm("no tasks present");
  }
  else {
    for (let i = 0; i < data.todos.length; i++){
      const row = document.createElement('tr');
      const cell1 = document.createElement('td');
      const cell2 = document.createElement('td');
      const cell3 = document.createElement('td');
      cell1.innerHTML = data.userId;
      cell2.innerHTML =  data.todos[i].task;
      cell3.innerHTML =data.todos[i].task;   
      row.appendChild(cell1);
      row.appendChild(cell2);
      row.appendChild(cell3);
      document.getElementById("userTodosResults").appendChild(row);
    } 
  }
  
}


function deleteUser(){
  let id = document.querySelector("#updateOrDelete").value;
  fetch('http://localhost:8080/user/' + id, {//2
    method: 'delete',//3
  })
  .then((data) => {
    console.log(`Request succeeded with JSON response ${data}`);
    // some function to execute if successful
  })
  .catch((error) => {
    //some function to execute if error
  });
  window.confirm("user deleted with id" + id);

}

function updateUser(){
  let id = document.querySelector("#updateOrDelete").value;
  let firstName = document.querySelector("#firstName").value;
  let lastName = document.querySelector("#lastName").value;
  let userName = document.querySelector("#userName").value;
  let email = document.querySelector("#email").value;
  let password = document.querySelector("#password").value;
  fetch("http://localhost:8080/user/" + id, { //1
    method: 'put', //2
    headers: {
      "Content-type": "application/json" //3
    },
    body: JSON.stringify( //4
      {
        "firstName": firstName,
        "lastName": lastName,
        "userName": userName,
        "email" : email,
        "password" : password
      }
    )
  });
  window.confirm("user updated with id" + id);


}



function fetchAllUsers(){
  clearData("userResultSet");
     fetch(`http://localhost:8080/user`) // 1
    .then((response) => {
        if (response.status !== 200) {  //  2
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() // 3
        //.then(data => console.info(data))
        .then(data  => parseUserData(data))// 4
    }).catch((err)=> console.error(`${err}`)); // 5

}

function getUserTasks(){
  let id = document.querySelector("#updateOrDelete").value;
     fetch(`http://localhost:8080/user/`+id) // 1
    .then((response) => {
        if (response === undefined){
          window.confirm("no tasks found");
        }
        else if (response.status !== 200) {  //  2
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() // 3
        //.then(data => console.info(data))
        .then(data => parseAndConvertToUserTaskTable(data))// 4
    }).catch((err)=> console.error(`${err}`)); // 5

}

function createUser(){

    let firstName = document.querySelector("#firstName").value;
    let lastName = document.querySelector("#lastName").value;
    let userName = document.querySelector("#userName").value;
    let email = document.querySelector("#email").value;
    let password = document.querySelector("#password").value;

    fetch("http://localhost:8080/user", { //1
    method: 'post', //2
    headers: {
      "Content-type": "application/json" //3
    },
    body: JSON.stringify( //4
      {
        "firstName": firstName,
        "lastName": lastName,
        "userName": userName,
        "email" : email,
        "password" : password
      }
    )
  })
  .then(res => res.json())
  .then((data) => console.log(`Request succeeded with JSON response ${data}`))
  .catch((error) => console.log(`Request failed ${error}`));

}



// function recordHistory(firstNumber, secondNumber,result, sign){
//     var row = document.createElement('tr');
//     var cell1 = document.createElement('td');
//     var cell2 = document.createElement('td');
//     var cell3 = document.createElement('td');
//     var cell4 = document.createElement('td');
//     cell1.innerHTML = firstNumber;
//     cell2.innerHTML = sign;
//     cell3.innerHTML = secondNumber;
//     cell4.innerHTML = result;
//     row.appendChild(cell1);
//     row.appendChild(cell2);
//     row.appendChild(cell3);
//     row.appendChild(cell4);
//     document.getElementById("resultset").appendChild(row);
// }


