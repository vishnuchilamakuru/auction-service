**Problem statement**

Design an auction system Foobar where there’ll be sellers who can create auction for item they want to sell. 
They’ll be specifying lowest and highest bid that can be placed and there will be a participation charge applicable 
on buyers who’ll be bidding for this auction. 

System should be able to handle multiple auctions at a time. 
Seller’s profit/loss will be calculated as follows : 
- WinningBid + 20% of totalParticipationCostByBuyers – averageOfLowestAndHighestBid. 
- Remaining 80% of participation cost goes to foobar as commission. 

Winning bid will be highest unique bid. If for a particular auction these are the bids 

- buyer1 19
- buyer2 19
- buyer3 17
- buyer4 17
- buyer5 10

then buyer5 will be the winner. 

Following operations were required :

- Create a seller
- create a buyer
- auction creation by a registered seller
- bid on an auction by any registered buyer
- bid amount can be updated by buyer
- Withdraw bid
- close an auction and return the winning bid
- profit/loss of any seller till now
- Brownie points for this : 
    - If any buyer has placed bid on more than 2 auctions than he is preferred buyer. So if in an auction there are two bids buyer1 19 and buyer2 19 and buyer2 is preferred buyer then he is the winner. If both buyer1 and buyer2 are preferred buyers then fall back to next highest bid and apply the same logic.

Expectations :

- Code should be moduler and all responsibilities shouldn’t be jammed in 1/2 classes. Basically code should be layered : dao layer, api layer etc...
- Code must be working and demoable.
- handle failures gracefully
- Cover corner cases
- Code must be extensible with minimal changes. So keep in mind the future requirements and use Interfaces and contracts wherever possible. You should be knowing SOLID principles and design patterns for this.
