<h1>ALGOW</h1>
ALGOW (Algorithm Wizard) is a program [made in multi-platform java, Eclipse] which executes algorithms in a wizard (in an interactive way).<br>
<br>

<h2>How does it work?</h2>
The main algorithm is read (located in "alg/start."), and shows the main question ("start").<br>
A number of options are showed to the user according to that question, and each option has its own code which can be, for example, going to another question.<br>
<br>

<h2>How are algorithms coded?</h2>
Each algorithm file (stored in the "alg" folder with the ".alg" extension) is coded using the ALGOWL (ALGOW Language) which has a very friendly syntax. In the next section there is more information about it.<br>
<br>
<br>


<h1>ALGOWL</h1>
ALGOWL (ALGOW Language) is the language with which all the algorithms for ALGOW are written. It's very easy to understand and it's not very different from XML.<br>
<br>

<h2>The basics</h2>
First of all, there are a few things you have to know about how ALGOWL works.<br>
There is ONE variable you can use (internally called "points"), this is because this program is not meant to resolve high-class mathematic or physic algorithms. This program is meant to resolve logic algorithms indeed (something like this: http://goo.gl/sMR0pz ).<br>
You can make changes and check the value of the variable.<br>
You can change the value adding or taking any value to that variable (negative values available!).<br>
You can check the value of the variable using logic operators.<br><br>

When you are creating an algorithm the first thing you have to do is create the main file. This is located in the folder "alg" (if it doesn't exist, open the program and it will be magically created (you can also create it manually)), and it's always called "start.alg", this will be the first algorithm to run. The main algorithm must have the main question ("start"), and as you are probably figuring out, it's the first question to run.<br>
<br>

<h2>Syntax</h2>
All ".alg" written in ALGOWL follow the same syntax. An algorithm is composed of questions. Each question is composed of one or more options (up to 9, enough for a single question). And each option is composed of one or more commands. Seems easy right? Well, it is.<br>
<br>

The questions are declared with a dot (' . ') followed by the (internal) name of the question, then a space and the caption of the question in quotes (' " ').<br>
The options are declared with an underscode (' _ ') followed by a space, and the caption of the question in quotes (' " ') (I know I wrote the sign before, but I want to make it clear).<br>
The commands are declared inside the options, and they don't need any declarer.<br>
<br>

<b>IMPORTANT:</b> The use of horizontal tabs is obligatory, and the syntactic spaces as well. The horizontal tabs CANNOT be replaced with spaces. The program will not work if you don't meet these requirements. Also, it's a good practice to use them in other languages, so this might be profitable for your learning.<br>
<br>

You can insert commentaries in your ".alg" file using the asterisk sign (' * ') before them.<br>
<br>

Another good feature of ALGOWL is that you can include some algorithms in others, so you don't have to worry about having all the questions in the same file, and it should be much better organized. You can include another ".alg" file using the at sign (' @ ') before.<br>
<br>

Here it is an example of all the syntax you have read before: http://pastebin.com/GSDznyYL<br>
<br>

<h2>Commands</h2>
There are only a few basic commands in ALGOWL, but there should be enoguh for any algorithm.<br>
All of them need NO space between the command and the parameter. Have caution with that.<br>
<br>

First of all, the command by excellence. This is called redirector. It's used with a hash sign (' # '), followed by the name of the question. For example: #likesicecream<br>
<br>

Then we have the commands that modify the variable:<br>
In one hand, we have the adder. This adds any value to the variable. It's used with a plus sign (' + '), followed by the value to add. For example: +3<br>
In the other hand, we have the taker. This takes any value to the variable. It's used with a less sign (' - '), followed by the value to take. For example: -2<br>
<br>

By last, we have the "end" command. This command finishes the algorithm with a message that is showed to the user. It's used with the "end" word, followed by a space and the message in quotes.<br>
<br>

<h2>Conditionals</h2>
In this section we will learn the conditionals we can use in ALGWL. There are 5, and all of them are followed by the value to check, a space and the command to do.<br>
<br>

The equals conditional. Checks if the variable has the same value as the input number. It can be used with double equals (' == '). E.g.: ==2 #valuetwo<br>
<br>

The greater-than conditional. Checks if the variable is greater than the input number. It can be used with a greater-than sign (' > '). E.g.: >3 end "Too many points"<br>
<br>

The greater-than or equals conditional. Checks if the variable is greater or the same as the input number. It can be used with a greater-than sign plus equals sign (' >= '). E.g.: >=0 #positivevalue<br>
<br>

The less-than conditional. Checks if the variable has less value than the input number. It is used with a less-than sign (' < '). E.g.: <0 #negative<br>
<br>

The less-than or equals conditional. Checks if the variable has less value or it's equals to the input number. It's used with a less-than sign plus equals sign (' <= '). E.g.: <=-3 #minusthreeorless<br>
<br>

<h2>Example</h2>
Once you have read the above documentation, you're ready to go! I am giving you an algorithm example, so you will be able to read it and see how the above syntax is applied. Here it is: http://pastebin.com/G8MF2Uiu