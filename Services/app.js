
const express = require('express');
var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://root:beanboards*@ds125198.mlab.com:25198/";
var bodyParser = require('body-parser');
var port = '8080';
//var port = '3000';
const app = express();
var path = require('path')

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.post('/addUserService', (req,result) => {
	
	const email = req.body.email;
	const password = req.body.password;

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var myobj = { email: email, password : password };
	  dbo.collection("user").insertOne(myobj, function(err, res) {
	    if(err)
		{
			result.send(JSON.stringify({id:0, value:"Some error encountered while registering.. Contact administrator"}));
		} 

		console.log("query executed successfully ..");
		result.send(JSON.stringify({id:1, value:"Please Login to Continue.. "}));
	    db.close();
	  });
	});
});

app.post('/loginService',(req,result)=> {

	const username = req.body.username;
	const email = req.body.email;
	const password = req.body.password;
	const confirmPassword = req.body.confirmPassword;
	const organization = req.body.organization;
	const phone = req.body.phone;

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var query = { username: username, email: email, password:password,confirmPassword: confirmPassword, organization:organization, phone:phone };
	  dbo.collection("user").find(query).toArray(function(err, res) {
	    if (err)
	    {
	    	throw err;	
	    } 
	    else
	    {
	    	console.log(res);
	    	if(res.length === 0)
	    	{
	    		result.send(JSON.stringify({id:0, value:"No User found..."}));
	    	}	
	    	else
	    	{
	    		result.send(JSON.stringify({id:1, value:"Logged In successfully..", email: email}));
	    	}
	    }
	    db.close();
	  });
	});
	
});

app.post('/addCommentsService',(req,result)=> {

	const column = req.body.column;
	const comments = req.body.username;

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards ");
	  var query = { column: column, comments: comments};
	  dbo.collection("comments").find(query).toArray(function(err, res) {
	    if (err)
	    {
	    	result.send(JSON.stringify({id:0, value:"Error adding comments..."}));
	    	throw err;	
	    } 
	    else
	    {
	    	result.send(JSON.stringify({id:1, value:"Comments added successfully..."}));
	    }
	    db.close();
	  });
	});
});

app.get('/',(req,res)=> {
	res.sendFile(path.join(__dirname + '/index.html'));
});
app.get('/MyBoards',(req,res)=> {
	res.sendFile(path.join(__dirname + '/MyBoards.html'));
});

app.get('/WorkFlow',(req,res)=> {
	res.sendFile(path.join(__dirname + '/workflow.html'));
});

app.get('/BoardsView',(req,res)=> {
	res.sendFile(path.join(__dirname + '/BoardView.html'));
});

app.use(express.static(__dirname + '/css'));

app.get('/commentBox,',(req,res)=>{
	res.sendFile(path.join(__dirname + '/commentBox.html'));
});

app.listen(port, () => {
	console.log('Server started  port '+ port);
});

