var db = require('./connect');

require('@google/cloud-debug');
exports.createRating = function createRating(req, res) {

    let id = req.body.id;
    let reservation_id = req.body.reservation_id
    let rating = req.body.rating;
    let comment = req.body.comment;
    let accommodation_id = req.body.accommodation_id;
    let isConfirmed = req.body.isConfirmed;
    let lastUpdated = req.body.lastUpdated;

    var sql = "INSERT INTO rating (id, reservation_id, rating, comment, accommodation_id, isConfirmed, lastUpdated) values (?, ?, ?, ?, ?, ?, ?)";

    db.query(sql,[id, reservation_id, rating, comment, accommodation_id, isConfirmed, lastUpdated], function (err, result) {
        if (err) 
            res.status(400).send('Bad request');
        else 
            res.status(200).send('Created');
    });
};

require('@google/cloud-debug');
exports.getRatingByReservationId = function getRatingByReservationId(req, res) {

    var sql = "select * from rating where id=" + req.query.id;

    db.query(sql, (err, result)=> {
        if (err) 
            res.status(404).send('Not Found');
        else 
            res.status(200).send(result[0]);
    });
};

require('@google/cloud-debug');
exports.getRatingByAccommodationId = function getRatingByAccommodationId(req, res) {

    var sql = "select * from rating where accommodation_id=" + req.query.accommodation_id;

    db.query(sql, (err, result)=> {
        if (err) 
            res.status(404).send('Not Found');
        else 
            res.status(200).send(result[0]);
    });
};