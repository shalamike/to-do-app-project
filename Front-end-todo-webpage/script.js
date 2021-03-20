'use strict';

// var resHistory = [];
console.log("hellooooo")

function parseData(data){
  console.log(data);
  console.log("now showing javascript object");
   let jsonObject = JSON.parse(JSON.stringify(data));
   let nestedArray = Object.values(jsonObject);
   let userList = nestedArray[0].userDTOList;
   console.log(nestedArray[0].userDTOList);

   convertUsersTotable(userList);

}

function convertUsersTotable(userList){

  for (let i = 0; i < userList.length; i++){
    const row = document.createElement('tr');
    const cell1 = document.createElement('td');
    const cell2 = document.createElement('td');
    const cell3 = document.createElement('td');
    const cell4 = document.createElement('td');
    const cell5 = document.createElement('td');
    //var cell6 = document.createElement('td');
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



function fetchAllUsers(){
     fetch(`http://localhost:8080/user`) // 1
    .then((response) => {
        if (response.status !== 200) {  //  2
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() // 3
        //.then(data => console.info(data))
        .then(data  => parseData(data))// 4
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


function fetchTodos(){
    fetch(`http://localhost:8080/todo`) // 1
    .then((response) => {
        if (response.status !== 200) {  //  2
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() // 3
        //.then(data => console.info(data)) // 4
        .then(data => parseData(data))
    }).catch((err)=> console.error(`${err}`)); // 5

}

function createToDos(){

  /*
                    <input type="text" class="form-control" id="task" placeholder="task:"/>
                    <input type="text" class="form-control" id="info" placeholder="more information about the task at hand"/>
                    <input type="text" class="form-control" id="startDate" placeholder="when are you going to start:"/>
                    <input type="text" class="form-control" id="dueDate" placeholder="when does it need to be completed:"/>
                    <input type="text" class="form-control" id="dateComplete" placeholder="when was it completed:"/>
                    <input type="text" class="form-control" id="userId" placeholder="who is working on this task"/>
  */

    let task = document.querySelector("#task").value;
    let info = document.querySelector("#info").value;
    let startDate = document.querySelector("#startDate").value;
    let dueDate = document.querySelector("#dueDate").value;
    let dateComplete = document.querySelector("#dateComplete").value;
    let userId = document.querySelector("#userId").value;

    fetch("http://localhost:8080/todo", { //1
    method: 'post', //2
    headers: {
      "Content-type": "application/json" //3
    },
    body: JSON.stringify( //4
        {
          "task": task,
          "info": info,
          "startDate" : startDate,
          "dueDate" : dueDate,
          "DateComplete" : dateComplete,
          "user" : {
              "userId": userId
          }    
      }
    )
  })
  .then(res => res.json())
  .then((data) => console.log(`Request succeeded with JSON response ${data}`))
  .catch((error) => console.log(`Request failed ${error}`));

}
    

function recordHistory(firstNumber, secondNumber,result, sign){
    var row = document.createElement('tr');
    var cell1 = document.createElement('td');
    var cell2 = document.createElement('td');
    var cell3 = document.createElement('td');
    var cell4 = document.createElement('td');
    cell1.innerHTML = firstNumber;
    cell2.innerHTML = sign;
    cell3.innerHTML = secondNumber;
    cell4.innerHTML = result;
    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    document.getElementById("resultset").appendChild(row);
}

function clearHistory(){
    let i = document.getElementById("resultset").rows.length -1;
    for(i; i>0; i--){
        document.getElementById("resultset").deleteRow(i);
    }
}


// function recordHistory(result){
//     resHistory.push(result);
// }

// function showHistory(){
//     for (var i = 0;i < resHistory.length; i++ ){
//         var row = document.createElement('tr');

//         var cell = document.createElement('td');

//         cell.innerHTML = resHistory[i];

//         row.appendChild(cell);

//         document.getElementById("resultset").appendChild(row);
//     }    
// }

