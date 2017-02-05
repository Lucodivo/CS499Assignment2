(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .controller('ArtistController', ArtistController);

    ArtistController.$inject = ['$scope', '$state', 'Artist'];

    function ArtistController ($scope, $state, Artist) {
        var vm = this;

        vm.artists = [];

        loadAll();

        function loadAll() {
            Artist.query(function(result) {
                vm.artists = result;
                vm.searchQuery = null;
            });
        }
    }
})();
