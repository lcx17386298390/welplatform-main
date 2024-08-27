function isValidPhone(str) {
  var myreg = /^[1][3,4,5,7,8,9][0-9]{9}$/;
  if (!myreg.test(str)) {
    return false;
  } else {
    return true;
  }
}

function isValidEmail(str) {
  var myreg = /^[a-z0-9]+([._-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
  if (!myreg.test(str)) {
    return false;
  } else {
    return true;
  }
}

module.exports = {
  isValidPhone,
  isValidEmail
}