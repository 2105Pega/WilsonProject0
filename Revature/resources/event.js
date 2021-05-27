function pow(event) {
  element=event.currentTarget;
  element.style.background="blue";
  alert(element.id)
  var Bob = [name = "Bod", age = 5];
  var Joe = [name = "Joe", age = 15];
  var Torn = [name = "Torn", age = 25];
  var People={Bob,Joe,Torn};

  for(person in People){
    console.log(person)
  }
}

window.onload=function(){
  document.getElementById("Table").addEventListener("click",pow, false);
}