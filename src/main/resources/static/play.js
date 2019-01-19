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

// Store images in object
let images = {
	rock: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/201958/Rock-Paper-Scissors-01.png",
	paper: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/201958/Rock-Paper-Scissors-02.png",
	scissors: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/201958/Rock-Paper-Scissors-03.png",
	question: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/201958/Rock-Paper-Scissors-04-04.png"
};

// Store alert components in object
let alertDiv = {
	loose: '<div class="alert alert-danger" role="alert"><strong>Loser!</strong> HaHaHa you looseee!</div>',
	win: '<div class="alert alert-success" role="alert"><strong>Wahoo you win!</strong> Lets play again!</div>',
	draw: '<div class="alert alert-info" role="alert"><strong>Close!</strong> it was a draw, please reset and try again.</div>'
};



// Track player score
let yourScore = 0;
let myScore = 0;

// Function to update images
const updateImg = (img, val) => {
	switch(val){
		case "Rock":
			img.attr('src', images.rock);
			break;
		case "Paper":
			img.attr('src', images.paper);
			break;
		case "Scissors":
			img.attr('src', images.scissors);
			break;
		default:
			img.attr('src', images.question);
	}
};

// Reset image to question mark
const resetImg = () => {
	myImgToUpdate.attr("src", images.question);
	myTextToUpdate.text("");
	yourImgToUpdate.attr("src", images.question);
	yourTextToUpdate.text("");
};


function loadImages(value) {
	 $("#imageBlock").load('/images',  {'playerMove':value});
}

function loadScores(value) {
	 $("#scoreBlock").load('/scores', {'playerMove':value});
}

$(document).ready( function() {
	resetImg();
		$('.btn-choice').click(function() {
		var value = $(this).text();
		loadImages(value);
		loadScores(value);
		updateImg(yourImgToUpdate, value);
	});
});

