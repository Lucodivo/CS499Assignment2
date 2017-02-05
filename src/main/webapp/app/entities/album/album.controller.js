(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .controller('AlbumController', AlbumController);

    AlbumController.$inject = ['$scope', '$state', 'Album'];

    function AlbumController ($scope, $state, Album) {
        var vm = this;

        vm.albums = [];

        loadAll();

        function loadAll() {
            Album.query(function(result) {
                vm.albums = result;
                vm.searchQuery = null;
            });
        }
    }
})();
