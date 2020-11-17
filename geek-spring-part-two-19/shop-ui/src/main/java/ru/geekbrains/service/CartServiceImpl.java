package ru.geekbrains.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    // Это поле указывает версию класса при сериализации стандартными средствами Java.
    private static final long serialVersionUID = -9025621122549454991L;
    // Но так как мы с вами подключили сериализацию через Json, то это поле не нужно
    // Java знает что с ним делать.

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final Map<LineItem, BigDecimal> lineItems;

    @PostConstruct
    public void post() {
        logger.info("Session bean post construct");
    }

    public CartServiceImpl() {
        this.lineItems = new HashMap<>();
    }

    @JsonCreator
    public CartServiceImpl(@JsonProperty("lineItems") List<LineItem> lineItems) {
        this.lineItems = lineItems.stream().collect(Collectors.toMap(li -> li, LineItem::getBidPrice));
    }

    @Override
    public void addProductBidPrice(ProductRepr productRepr, BigDecimal bidPrice) {
        LineItem lineItem = new LineItem(productRepr, bidPrice);
        lineItems.put(lineItem, bidPrice);
    }

    @Override
    public void removeProduct(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    @Override
    public List<LineItem> getLineItems() {
        lineItems.forEach(LineItem::setBidPrice);
        return new ArrayList<>(lineItems.keySet());
    }

    @Override
    public void updateCart(LineItem lineItem) {
        lineItems.put(lineItem, lineItem.getBidPrice());
    }

    @JsonIgnore
    @Override
    public BigDecimal getSubTotal() {
        lineItems.forEach(LineItem::setBidPrice);
        return lineItems.keySet().stream()
                .map(LineItem::getBidPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
