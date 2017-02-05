(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .controller('SongController', SongController);

    SongController.$inject = ['$scope', '$state', 'Song'];

    function SongController ($scope, $state, Song) {
        var vm = this;

        vm.songs = [];

        loadAll();

        function loadAll() {
            Song.query(function(result) {
                vm.songs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
