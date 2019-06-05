var mysql = require('mysql');

let db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'MySqlPassword123!',
    database: "reservations"
});

db.connect((err) => {
    if (err) {
        throw err;
    }
    console.log('Connected to database ');

});

module.exports = db;
