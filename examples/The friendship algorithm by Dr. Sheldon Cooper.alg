:begin {
	end "BEGIN FRIENDSHIP!"
}

:start {
	do "Place phone call"
	#home
}
.home "Home?" {
	_ "Yes" {
		$meal
	}
	_ "No" {
		$leave
	}
}
:leave {
	do "Leave message"
	do "Wait for callback"
	$meal
}
:meal {
	do "Would you like to share a meal?"
	#mealresp
}
.mealresp "What is the response?" {
	_ "Yes" {
		$dinetogether
	}
	_ "No" {
		$hot
	}
}
:dinetogether {
	do "Dine together"
	$begin
}
:hot {
	do "Do you enjoy a hot beverage?"
	#hotresp
}
.hotresp "What is the response?" {
	_ "Yes" {
		#case
	}
	_ "No" {
		$rec
	}
}
.case "CASE" {
	_ "Tea" {
		do "Have tea"
		$begin
	}
	_ "Coffee" {
		do "Have coffee"
		$begin
	}
	_ "Cocoa" {
		do "Have cocoa"	
		$begin	
	}
}
:rec {
	do "Recreational activity? Tell me one of your interests."
	#share
}
.share "Do I share that interest?" {
	_ "Yes" {
		do "Why don't we do that together?"
		$part
	}
	_ "No" {
		$loop
	}
}
:part {
	do "Partake in interest"
	$begin
}
:loop {
	+1
	>6 $choose
	$rec
}
:choose {
	do "Choose least objectionable interest"
	$part
}