package server;

import java.util.*;

public class BidDAOImpl implements BidDAO {

    private final Set<BidDTO> bids;

    private final Random random;

    public BidDAOImpl() {
        bids = new LinkedHashSet<>();
        random = new Random();
    }

    public void addBid(BidDTO bid) {
        if (bid == null) {
            throw new RuntimeException("Bid should not be null");
        }
        bid.setBidID(random.nextInt(100));
        bids.add(bid);
    }

    @Override
    public Set<BidDTO> getBids() {
        return Collections.unmodifiableSet(bids);
    }
}
