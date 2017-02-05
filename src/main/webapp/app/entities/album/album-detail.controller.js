(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .controller('AlbumDetailController', AlbumDetailController);

    AlbumDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Album', 'Artist', 'Song'];

    function AlbumDetailController($scope, $rootScope, $stateParams, previousState, entity, Album, Artist, Song) {
        var vm = this;

        vm.album = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('cs499Assignment2App:albumUpdate', function(event, result) {
            vm.album = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
