var db = require('./connect');

require('@google/cloud-debug');
exports.createRating = function createRating(req, res) {

    let reservation_id = req.body.reservation_id
    let rating = req.body.rating;
    let comment = req.body.comment;
    let accommodation_id = req.body.accommodation_id;
    let verified = req.body.verified;

    var sql = "INSERT INTO rating (reservation_id, rating, comment, accommodation_id, verified) values (?, ?, ?, ?, ?)";

    db.query(sql,[reservation_id, rating, comment, accommodation_id, verified], function (err, result) {
        if (err) 
            res.status(400).send('Bad Request');
        else 
            res.status(200).send('Created');
    });
};

require('@google/cloud-debug');
exports.getRatingByReservationId = function getRatingByReservationId(req, res) {

    var sql = "select * from rating where reservation_id=" + req.query.id;

    db.query(sql, (err, result)=> {
        if (err) 
            res.status(404).send('Not Found');
        else 
            res.status(200).send(result);
    });
};

require('@google/cloud-debug');
exports.getRatingByAccommodationId = function getRatingByAccommodationId(req, res) {

    var sql = "select * from rating where accommodation_id=" + req.query.accommodation_id;

    db.query(sql, (err, result)=> {
        if (err) 
            res.status(404).send('Not Found');
        else 
            res.status(200).send(result);
    });
};

require('@google/cloud-debug');
exports.getRatings = function getRatings(req, res) {

    var sql = "select * from rating";

    db.query(sql, (err, result)=> {
        if (err) 
            res.status(404).send('Not Found');
        else 
            res.status(200).send(result);
    });
};

require('@google/cloud-debug');
exports.verified = function verified(req, res) {

    var sql = "UPDATE rating SET verified = 1 WHERE id=" + req.query.id;

    db.query(sql, (err, result)=> {
        if (err) 
            res.status(404).send('Not Found');
        else 
            res.status(200).send(result);
    });
};