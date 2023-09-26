const bookmarkMain = {
    init: function() {
        const _this = this;

        $('#bookmark-btn-save').on('click', function () {
            _this.save();
        });

        $('#bookmark-btn-delete').on('click', function (e) {
            const bookmarkId = e.target.value;
            _this.delete(bookmarkId);
        });
    },

    save: function () {
        const userId = $('#userId-bookmark').val();
        const stationId = $('#stationId-bookmark').val();
        const data = {
            userId: userId,
            stationId: stationId
        }

        $.ajax({
            type: 'POST',
            url: '/bookmarks/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.href = '/stations/' + stationId;
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },

    delete: function (bookmarkId) {
        const stationId = $('#stationId-bookmark').val();

        $.ajax({
            type: 'DELETE',
            url: '/bookmarks/' + bookmarkId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            window.location.href = '/stations/' + stationId;
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};

bookmarkMain.init();