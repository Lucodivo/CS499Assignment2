(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .controller('ArtistDetailController', ArtistDetailController);

    ArtistDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Artist', 'Album'];

    function ArtistDetailController($scope, $rootScope, $stateParams, previousState, entity, Artist, Album) {
        var vm = this;

        vm.artist = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('cs499Assignment2App:artistUpdate', function(event, result) {
            vm.artist = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
