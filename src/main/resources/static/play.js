// Get elements to update
const yourImgToUpdate = $('#yourChoiceImg');
const yourTextToUpdate = $('#yourChoiceText');
const myImgToUpdate = $('#myChoiceImg');
const myTextToUpdate = $('#myChoiceText');
const alertResult = $('#alertResult');
const alertModal = $('#resultModal');
const alertYourScore = $('#yourScore');
const alertMyScore = $('#myScore');
const recordYourScore = $('#yourScore span');
const recordMyScore = $('#myScore span');
const startOver = $('#startOver');
const letsPlay = $('#letsPlay');

loadScores()
loadImages()

function loadImages(value) {
	 $("#imageBlock").load('/images',  {'playerMove':value}, loadScores(value));
}

function loadScores(value) {
	 $("#scoreBlock").load('/scores', {'playerMove':value});
}

var ldImg = function(value) {
	var r = $.Deferred();
	$("#imageBlock").load('/images',  {'playerMove':value});
	return r;
};

var ldScore = function(value){
	$("#scoreBlock").load('/scores', {'playerMove':value});
};


$(document).ready( function() {
		$('.btn-choice').click(function() {
		var value = $(this).text();
		ldImg(value).done( ldScore(value));
	});
});


