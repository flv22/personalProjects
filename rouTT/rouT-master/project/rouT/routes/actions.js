var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  // res.render('customize');
  res.render('actions');
});

module.exports = router;