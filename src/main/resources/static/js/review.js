const reviewMain = {
    init: function() {
        const _this = this;

        $('#review-btn-save').on('click', function () {
            _this.save();
        });

        $('#content').on('keypress', function (e) {
            if (e.keyCode == '13') {
                $('#review-btn-save').click();
            }
        })

        $('.review-btn-delete').on('click', function (e) {
            const reviewId = e.target.value;
            _this.delete(reviewId);
        });
    },

    save: function () {
        const stationId = $('#stationId').val();
        const content = $('#content').val()
        const data = {
            stationId: stationId,
            content: content
        }

        $.ajax({
            type: 'POST',
            url: '/reviews/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.href = '/stations/' + stationId;
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },

    delete: function (reviewId) {
        const stationId = $('#stationId').val();

        $.ajax({
            type: 'DELETE',
            url: '/reviews/' + reviewId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            window.location.href = '/stations/' + stationId;
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};

reviewMain.init();