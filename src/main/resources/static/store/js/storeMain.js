$(document).ready(function () {
    $(".slider").bxSlider({
        auto: true,
        pause: 5000,
        minSlides: 3,
        slideWidth: 1376,
    });

    $(".bxslider").bxSlider({
        minSlides: 4,
        maxSlides: 4,
        slideWidth: 1376,
        slideMargin: 50,
    });
});