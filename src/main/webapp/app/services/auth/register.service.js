(function () {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
