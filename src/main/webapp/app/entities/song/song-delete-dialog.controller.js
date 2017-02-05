(function() {
    'use strict';

    angular
        .module('cs499Assignment2App')
        .controller('SongDeleteController',SongDeleteController);

    SongDeleteController.$inject = ['$uibModalInstance', 'entity', 'Song'];

    function SongDeleteController($uibModalInstance, entity, Song) {
        var vm = this;

        vm.song = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Song.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
