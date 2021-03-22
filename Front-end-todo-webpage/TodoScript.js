'use strict';

function parseTodoData(data){
    let jsonObject = JSON.parse(JSON.stringify(data));
    let TodoArray = Object.values(jsonObject);
    console.log(TodoArray);
    converTodosTotable(TodoArray)
    

  }

  function converTodosTotable(todoArray){
    for (let i = 0; i < todoArray.length; i++){
      const row = document.createElement('tr');
      const cell1 = document.createElement('td');
      const cell2 = document.createElement('td');
      const cell3 = document.createElement('td');

      cell1.innerHTML = todoArray[i].todoId;
      cell2.innerHTML = todoArray[i].task;
      cell3.innerHTML = todoArray[i].info;

      row.appendChild(cell1);
      row.appendChild(cell2);
      row.appendChild(cell3);

      document.getElementById("TodoResultSet").appendChild(row);
    }
  }
  
  function fetchTodos(){
    clearData("TodoResultSet");
      fetch(`http://localhost:8080/todo`) // 1
      .then((response) => {
          if (response.status !== 200) {  //  2
              console.error(`status: ${reponse.status}`);
              return;
          }
          response.json() // 3
          //.then(data => console.info(data)) // 4
          .then(data => parseTodoData(data))
      }).catch((err)=> console.error(`${err}`)); // 5
  
  }
  
  function createToDos(){
  
      let task = document.querySelector("#task").value;
      let info = document.querySelector("#info").value;
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


  function clearData(table){
    let i = document.getElementById(table).rows.length -1;
    for(i; i>0; i--){
        document.getElementById(table).deleteRow(i);
    }
  }