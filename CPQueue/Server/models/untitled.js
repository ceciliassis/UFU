var buckets = require('buckets-js');
var usuario = require('./user2');

var q = new buckets.PriorityQueue(Compare);

var xD = new usuario.User(11,"Delicinha",2);
q.add(xD);
q.add(new usuario.User(3,"vinicius",3));
q.add(new usuario.User(1,"igor",4));
q.add(new usuario.User(6,"cecilia",1));
q.add(new usuario.User(7,"ibayara",5));
console.log(q.size());
console.log(q);
console.log(getPosition(q,"vinicius"));
console.log("acabou");
function getPosition(queue, xx){
	var fila = queue.toArray();
	var k = new buckets.PriorityQueue(Compare);
	for(i = 0; i < queue.size(); i++) k.add(fila[i]);
	i = 0;
	while(!k.isEmpty()) {fila[i++] = k.dequeue();console.log(fila[i-1].getName()+ " " + fila[i-1].getTimeWait()) ;}

	for(i = 0; i < queue.size(); i++) if(fila[i].getName() == xx) return i + 1;
		
}

function Compare(userA, userB) {
  if (userA.timeWait > 1200)
    return userA.getTimeWait() - userB.getTimeWait();
  if (userA.getPeople() >= 4 && userA.getPeople() % 4 === 0 && userB.getPeople() % 4 === 0 && userB.getPeople() >= 4)
    return userA.getPeople() - userB.getPeople();
  if (userB.getPeople() >= 4 && userB.getPeople() % 4 === 0)
    return -1;
  if (userA.getPeople() >= 4 && userA.getPeople() % 4 === 0)
    return 1;
  return userA.getPeople() - userB.getPeople();
}