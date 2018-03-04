
const express = require('express');
var MongoClient = require('mongodb').MongoClient;
var mongodb = require('mongodb');
var url = "mongodb://root:beanboards*@ds125198.mlab.com:25198/beanboards";
//var url = "mongodb://localhost:27017/";
var bodyParser = require('body-parser');
var port = '8080';
//var port = '3000';
const app = express();
var path = require('path')

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static(__dirname));

app.post('/addUserService', (req,result) => {
	
	const username = req.body.username;
	const email = req.body.email;
	const password = req.body.password;
	const confirmPassword = req.body.confirmPassword;
	const organization = req.body.organization;
	const phone = req.body.phone;

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var myobj = { username : username, email: email, password : password, confirmPassword: confirmPassword, organization: organization, phone: phone };
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

app.post('/addBoardService', (req,result) => {

	console.log("Entered Service addBoardService");
	var moduleName = req.body.moduleName; 
	console.log("modulename: "+moduleName);
	
	var public = "yes";
	var type = moduleName;
	var name = "NewBoard";
	var peek = "yes";
	var watchers =1;
	var likes = 1;

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var myobj = { public: public, type: type, name: name, peek : peek, watchers: watchers, likes: likes};
	  dbo.collection("boards").insertOne(myobj, function(err, res) {
	    if(err)
		{
		//	result.send(JSON.stringify({id:0, value:"Some error encountered while registering.. Contact administrator"}));
			result.send("error");
		} 

		console.log("query executed successfully ..");
		//result.send(JSON.stringify({id:1, value:"Board created successfully.. "}));
		result.send("query executed successfully");
	    db.close();
	  });
	});
});

app.get('/deleteBoardService', (req,result) => {

	console.log("Entered Service deleteBoardService");
	
	var _id = req.param('_id');

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  console.log(_id);
	var query = {_id: new mongodb.ObjectID(_id)};
	  dbo.collection("boards").deleteOne(query, function(err, res) {
	    if(err)
		{
		//	result.send(JSON.stringify({id:0, value:"Some error encountered while registering.. Contact administrator"}));
			result.send("error");
		} 
		console.log("query executed successfully ..");
		//result.send(JSON.stringify({id:1, value:"Board created successfully.. "}));
		result.send("query executed successfully");
	    db.close();
	  });
	});
});


app.get('/deleteWorkFlowService', (req,result) => {

	console.log("Entered Service deleteWorkFlowService...");
	
	var _id = req.param('_id');

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  console.log(_id);
	var query = {_id: new mongodb.ObjectID(_id)};
	  dbo.collection("workflow").deleteOne(query, function(err, res) {
	    if(err)
		{
		//	result.send(JSON.stringify({id:0, value:"Some error encountered while registering.. Contact administrator"}));
			result.send("error");
		} 
		console.log("query executed successfully ..");
		//result.send(JSON.stringify({id:1, value:"Board created successfully.. "}));
		result.send("query executed successfully");
	    db.close();
	  });
	});
});

app.get('/deleteBoardLevelComment', (req,result) => {

	console.log("Entered Service deleteWorkFlowService...");
	
	var _id = req.param('_id');

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  console.log(_id);
	var query = {_id: new mongodb.ObjectID(_id)};
	  dbo.collection("comments").deleteOne(query, function(err, res) {
	    if(err)
		{
		//	result.send(JSON.stringify({id:0, value:"Some error encountered while registering.. Contact administrator"}));
			result.send("error");
		} 
		console.log("query executed successfully ..");
		//result.send(JSON.stringify({id:1, value:"Board created successfully.. "}));
		result.send("query executed successfully");
	    db.close();
	  });
	});
});


app.post('/loginService',(req,result)=> {

	const email = req.body.email;
	const password = req.body.password;

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var query = {email: email, password:password};
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

	const cValue = req.body.cValue;
	const boardName = req.body.boardName;

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var data = { boardName: boardName, cValue: cValue};
	  dbo.collection("comments").insertOne(data, function(err, res) {
	    if(err)
		{
		//	result.send(JSON.stringify({id:0, value:"Some error encountered while registering.. Contact administrator"}));
			result.send("error");
		} 

		console.log("comments"+cValue+" added successfully ..");
		//result.send(JSON.stringify({id:1, value:"Board created successfully.. "}));
		result.send("query executed successfully");
	    db.close();
	  });
	});
});

app.post('/addWorkFlowService', (req,result) => {

	console.log("Entered Service addWorkFlowService");
	
	var newWorkFlow = "New";

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var myobj = { newWorkFlow: newWorkFlow};
	  dbo.collection("workflow").insertOne(myobj, function(err, res) {
	    if(err)
		{
		//	result.send(JSON.stringify({id:0, value:"Some error encountered while registering.. Contact administrator"}));
			result.send("error");
		} 

		console.log("query executed successfully ..");
		//result.send(JSON.stringify({id:1, value:"Board created successfully.. "}));
		result.send("query executed successfully");
	    db.close();
	  });
	});
});



app.get('/getBoardsService',(req,result)=>{

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  dbo.collection("boards").find().sort({_id:-1}).toArray(function(err, res) {
	    if (err)
	    {
	    	throw err;	
	    } 
	    else
	    {
	    	console.log(res);
	    	result.send(res);
	    }
	    db.close();
	  });
	});
});

app.get('/getModuleName',(req,result)=>{

	var id = req.param('_id');
	console.log("board name id:"+id);
	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  
	  var dbo = db.db("beanboards");
	  var query = {_id: new mongodb.ObjectID(id)};
	  dbo.collection("boards").find(query).toArray(function(err, res) {
	    if (err)
	    {
	    	throw err;	
	    } 
	    else
	    {
	    	console.log("BoardName: "+res);
	    	result.send(res);
	    }
	    db.close();
	  });
	});
});

app.get('/getCommentsService',(req,result)=>{

	var name = req.param('boardName');
	
	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var query = {boardName:name};
	  dbo.collection("comments").find(query).sort({_id:-1}).toArray(function(err, res) {
	    if (err)
	    {
	    	throw err;	
	    } 
	    else
	    {
	    	console.log(res);
	    	result.send(res);
	    }
	    db.close();
	  });
	});
});

app.post('/updateTitle',(req,result)=>{

	var id = req.body._id;
	var value = req.body.title;
	/*console.log(id);
	console.log(value);*/

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var myquery = { _id: 	new mongodb.ObjectID(id) };
	  var newvalues = { $set: {newWorkFlow: value } };
	  dbo.collection("workflow").updateOne(myquery, newvalues, function(err, res) {
	    if (err) throw err;
	    console.log("query executed successfully.. document Updated");
	    result.send(res);
	    db.close();
	  });
	});

});

app.post('/updateBoardComments',(req,result)=>{

	var id = req.body._id;
	var value = req.body.text;
	/*console.log(id);
	console.log(value);*/

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var myquery = { _id: 	new mongodb.ObjectID(id) };
	  var newvalues = { $set: {cValue: value } };
	  dbo.collection("comments").updateOne(myquery, newvalues, function(err, res) {
	    if (err) throw err;
	    console.log("comments"+value+" added successfully.. document Updated");
	    result.send(res);
	    db.close();
	  });
	});

});

app.post('/updateBoardTitle',(req,result)=>{

	var id = req.body._id;
	var value = req.body.title;
	/*console.log(id);
	console.log(value);*/

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  var myquery = { _id: 	new mongodb.ObjectID(id) };
	  var newvalues = { $set: {name: value } };
	  dbo.collection("boards").updateOne(myquery, newvalues, function(err, res) {
	    if (err) throw err;
	    console.log("query executed successfully.. document Updated");
	    result.send(res);
	    db.close();
	  });
	});

});
app.get('/getWorkFlowService',(req,result)=>{

	MongoClient.connect(url, function(err, db) {
	  if (err) throw err;
	  var dbo = db.db("beanboards");
	  dbo.collection("workflow").find().toArray(function(err, res) {
	    if (err)
	    {
	    	throw err;	
	    } 
	    else
	    {
	    	console.log(res);
	    	result.send(res);
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


app.get('/commentBox,',(req,res)=>{
	res.sendFile(path.join(__dirname + '/commentBox.html'));
});

app.listen(port, () => {
	console.log('Server started  port '+ port);
});

