package server;

import java.util.Set;

public interface BidDAO {

    void addBid(BidDTO bid);

    Set<BidDTO> getBids();
}
