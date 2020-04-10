package com.foobar;

import com.foobar.controller.AuctionController;
import com.foobar.controller.BidController;
import com.foobar.controller.BuyerController;
import com.foobar.controller.SellerController;
import com.foobar.domain.Auction;
import com.foobar.domain.Bid;
import com.foobar.domain.Buyer;
import com.foobar.domain.Seller;
import com.foobar.exception.FooBarException;
import com.foobar.service.AuctionService;
import com.foobar.service.BidService;
import com.foobar.service.BuyerService;
import com.foobar.service.SellerService;
import com.foobar.service.factory.AuctionProcessorFactory;
import com.foobar.service.factory.HighestUniqueBidProcessor;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class FooBarBidTest {
    public static void main(String[] args) throws FooBarException {
        final SellerService sellerService = new SellerService();
        final SellerController sellerController = new SellerController(sellerService);

        final Seller seller = new Seller();
        seller.setName("seller1");

        final Seller seller1 = sellerController.createSeller(seller);
        System.out.println(sellerController.findById(seller1.getIdentifier()));

        final BuyerService buyerService = new BuyerService();
        final BuyerController buyerController = new BuyerController(buyerService);

        Buyer buyer1 = new Buyer();
        buyer1.setName("buyer1");
        Buyer buyer2 = new Buyer();
        buyer2.setName("buyer2");
        Buyer buyer3 = new Buyer();
        buyer3.setName("buyer3");
        Buyer buyer4 = new Buyer();
        buyer4.setName("buyer4");
        Buyer buyer5 = new Buyer();
        buyer5.setName("buyer5");

        buyer1 = buyerController.createBuyer(buyer1);
        buyer2 = buyerController.createBuyer(buyer2);
        buyer3 = buyerController.createBuyer(buyer3);
        buyer4 = buyerController.createBuyer(buyer4);
        buyer5 = buyerController.createBuyer(buyer5);

        System.out.println(buyerController.findById(buyer1.getIdentifier()));
        System.out.println(buyerController.findById(buyer2.getIdentifier()));
        System.out.println(buyerController.findById(buyer3.getIdentifier()));
        System.out.println(buyerController.findById(buyer4.getIdentifier()));
        System.out.println(buyerController.findById(buyer5.getIdentifier()));

        final AuctionService auctionService = new AuctionService(sellerService);
        final AuctionController auctionController = new AuctionController(auctionService);

        Auction auction = new Auction();
        auction.setName("auction1");
        auction.setMaxBidAmount(20d);
        auction.setMinBidAmount(9d);
        auction.setSeller(seller1);

        auction = auctionController.create(auction);
        System.out.println(auction);

        final BidService bidService = new BidService(auctionService, buyerService);
        final BidController bidController = new BidController(bidService);

        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setBidAmount(10d);
        bid.setBuyer(buyer1);
        bid = bidController.create(bid);

        Bid bid2 = new Bid();
        bid2.setAuction(auction);
        bid2.setBidAmount(10d);
        bid2.setBuyer(buyer2);
        bid2 = bidController.create(bid2);

        Bid bid3 = new Bid();
        bid3.setAuction(auction);
        bid3.setBidAmount(15d);
        bid3.setBuyer(buyer3);
        bid3 = bidController.create(bid3);

        Bid bid4 = new Bid();
        bid4.setAuction(auction);
        bid4.setBidAmount(15d);
        bid4.setBuyer(buyer4);
        bid4 = bidController.create(bid4);

        Bid bid5 = new Bid();
        bid5.setAuction(auction);
        bid5.setBidAmount(15d);
        bid5.setBuyer(buyer5);
        bid5 = bidController.create(bid5);

        System.out.println(bid);
        System.out.println(bid2);
        System.out.println(bid3);
        System.out.println(bid4);
        System.out.println(bid5);

        //        bid5.setBidAmount(15d);
        //        bidController.update(bid5.getIdentifier(), bid5);
        //        System.out.println(bid5);

        //        bidController.withdraw(bid4.getIdentifier());
        //        System.out.println(bid4);

        System.out.println("===== Bids Status Before Auction Close======");
        System.out.println(bid);
        System.out.println(bid2);
        System.out.println(bid3);
        System.out.println(bid4);
        System.out.println(bid5);

        System.out.println("====== Closing Auction =======");
        AuctionProcessorFactory.register(new HighestUniqueBidProcessor(auctionService, bidService));
        auction = auctionController.close(auction.getIdentifier());
        System.out.println(auction);

        System.out.println("===== Bids Status After Auction Close======");
        System.out.println(bid);
        System.out.println(bid2);
        System.out.println(bid3);
        System.out.println(bid4);
        System.out.println(bid5);

        Auction auction2 = new Auction();
        auction2.setName("auction2");
        auction2.setMaxBidAmount(15d);
        auction2.setMinBidAmount(9d);
        auction2.setSeller(seller1);

        auction2 = auctionController.create(auction2);
        System.out.println(auction2);

        Buyer buyer6 = new Buyer();
        buyer6.setName("buyer6");
        buyer6 = buyerController.createBuyer(buyer6);


        Bid bida = new Bid();
        bida.setAuction(auction2);
        bida.setBidAmount(10d);
        bida.setBuyer(buyer1);
        bida = bidController.create(bida);

        Bid bidb = new Bid();
        bidb.setAuction(auction2);
        bidb.setBidAmount(10d);
        bidb.setBuyer(buyer6);
        bidb = bidController.create(bidb);

        System.out.println("===== Bids Status Before Auction2 Close======");
        System.out.println(bida);
        System.out.println(bidb);

        System.out.println("====== Closing Auction2 =======");
        AuctionProcessorFactory.register(new HighestUniqueBidProcessor(auctionService, bidService));
        auction2 = auctionController.close(auction2.getIdentifier());
        System.out.println(auction2);

        System.out.println("===== Bids Status After Auction2 Close======");
        System.out.println(bida);
        System.out.println(bidb);


    }
}
