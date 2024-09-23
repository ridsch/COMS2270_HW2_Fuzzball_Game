/*
Here is how it works. There are two teams, which we will call "Team 0" and "Team 1" that take
turns as the batting team and the fielding team. At each step of the game, a player called the
pitcher from the fielding team, standing in a designated location, throws the ball towards a player
on the batting team called the batter, also standing in a designated location called home. The
batter, at his or her discretion, tries to hit the ball with a stick and make it go a very long
distance. Another player from the fielding team, called the catcher, crouches behind the batter
and catches the ball if the batter doesn't hit it. Behind the catcher is an official called the umpire,
whose job is to decide whether the pitch was accurately thrown. Some possible outcomes are:
• The batter swings the stick at the ball and misses. This is a strike, and the batter is
immediately out.
• The batter doesn't swing at the ball, but the umpire declares that it was accurately thrown.
This is a called strike. The batter isn't immediately out, but the called strike is added to a
count for that batter, and after a certain number of them, the batter is out.
• The batter doesn't swing at the ball, but the umpire declares that it wasn’t accurately
thrown. This is called a ball, and is added to the batter's count of balls. What this means
is discussed below.
• The batter hits the ball, but it goes less than 15 feet, or flies behind the batter. This is a
foul, and the batter is immediately out.
• The batter hits the ball, but someone on the fielding team catches it before it bounces.
This is a caught fly, and the batter is immediately out.
• The batter hits the ball and it goes 15 feet or more, in the right direction, and isn't caught
by the fielding team. This is a hit. Depending on the distance the ball goes, it is called a
single, double, triple, or home run, the consequences of which are discussed below.

Outs:
If a batter is out, one of two things happens: if the number of outs for the batting team has
reached a designated maximum (usually 3), the teams switch sides: the fielding team becomes
the batting team, and the batting team becomes the fielding team, and all the imaginary runners
(see below) are cleared off the bases. In any case, the counts of balls and strikes are reset to zero
for a new batter. 

Hits:
Close your eyes, breathe deeply, and imagine three locations. Everyone playing the game has in
his or her mind a picture of three locations, known as first base, second base, and third base. A
base may be occupied by an imaginary runner. When a batter hits a single, an imaginary runner
goes to first base. Any imaginary runners that were already on the imaginary bases automatically
advance to the next one: first base to second base, second base to third base, and third base to
"home". Each time an imaginary runner advances to home, the team earns a point. This is
important, because it's by accumulating points that you win the game!
When the batter hits a double, the same process essentially happens twice: an imaginary runner
goes to second base, leaving first base empty, and any runners already on the bases advance
twice. (Note that an imaginary runner reaching home always stops there, and does not keep going
around to first base again!) Likewise, in case of a triple, an imaginary runner goes to third base,
and any runners already on base advance three times. In case of a home run, the runners advance
four times, so the bases always end up empty, and the team always earns at least one point, and
possibly up to four points.
After any hit or out, the counts of balls and strikes is reset to zero for a new batter.

Singles, Doubles, Triples, Home Runs:
If the batter hits the ball but it travels less than 15 feet in front of the batter, or goes a negative
distance (behind the batter), as noted above it's considered a foul and the batter is out. Otherwise,
as long as it's not a caught fly, it's a hit, and the interpretation of the distance the ball travels is as
follows:
1. At least 15 feet but less than 150: the hit is a single
2. At least 150 feet but less than 200: the hit is a double
3. At least 200 feet but less than 250: the hit is a triple
4. 250 feet or more: the hit is a home run 

Balls:
If the batting player's count of balls reaches a certain point (usually 5), the pitching stops and the
batter gets what is called a walk. What this means is that, as with a single, an imaginary runner is
placed on first base. However, the imaginary runners on other bases don't automatically advance
one base as they do for a hit; they only do so if forced to by the advancement of another
imaginary runner. As an example: suppose there are runners on first and third base and the batter
gets a walk. The new imaginary runner is placed on first, the runner on first is forced to advance
to second, but the batter on third stays there. After a walk, the counts of balls and strikes are reset
to zero for a new batter.

Innings, "top" and "bottom", and winning the game:
The game is divided into rounds called innings. In each inning, each team gets a chance to be the
batting team, always starting with Team 0; when the designated number of outs is reached, Team
1 becomes the batting team. The maximum number of innings for a game is configurable via a
constructor parameter. Whichever team has the most points after the last inning is the winner. It
is possible to have a tie, in which case both teams are considered to "win" the game. As a matter
of terminology, the first part of the inning when Team 0 is batting is called the top, and the
second part when Team 1 is batting is called the bottom. 

*/
