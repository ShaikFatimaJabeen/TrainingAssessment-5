
import java.util.*;
import java.util.stream.*;
import java.util.function.Function;


class Stock {
    private String ticker;
    private double price;
    private String date;

    public Stock(String ticker, double price, String date) {
        this.ticker = ticker;
        this.price = price;
        this.date = date;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Stock{" + "ticker='" + ticker + "', price=" + price + ", date='" + date + "'}";
    }
}

public class StockMarketDataAnalysis {
    public static void main(String[] args) {
       
        List<Stock> stocks = Arrays.asList(
            new Stock("AAPL", 145.0, "2025-04-01"),
            new Stock("GOOG", 2725.0, "2025-04-01"),
            new Stock("AMZN", 3342.0, "2025-04-01"),
            new Stock("MSFT", 300.0, "2025-04-01"),
            new Stock("AAPL", 148.0, "2025-04-02"),
            new Stock("GOOG", 2750.0, "2025-04-02"),
            new Stock("AMZN", 3370.0, "2025-04-02"),

        );

        
        String tickerToFind = "AAPL";
        Optional<Stock> highestStockPrice = stocks.stream()
            .filter(stock -> stock.getTicker().equals(tickerToFind))
            .max(Comparator.comparingDouble(Stock::getPrice));

        highestStockPrice.ifPresentOrElse(
            stock -> System.out.println("Highest price for " + tickerToFind + ": " + stock),
            () -> System.out.println("No data found for ticker: " + tickerToFind)
        );
        System.out.println("----------------------------");

        
        String tickerToFindLow = "GOOG";
        Optional<Stock> lowestStockPrice = stocks.stream()
            .filter(stock -> stock.getTicker().equals(tickerToFindLow))
            .min(Comparator.comparingDouble(Stock::getPrice));

        lowestStockPrice.ifPresentOrElse(
            stock -> System.out.println("Lowest price for " + tickerToFindLow + ": " + stock),
            () -> System.out.println("No data found for ticker: " + tickerToFindLow)
        );
        System.out.println("----------------------------");

        
        double averagePrice = stocks.stream()
            .mapToDouble(Stock::getPrice)
            .average()
            .orElse(0.0);
        System.out.println("Average stock price: " + averagePrice);
        System.out.println("----------------------------");

        
        Optional<Stock> highestPriceStock = stocks.stream()
            .max(Comparator.comparingDouble(Stock::getPrice));

        highestPriceStock.ifPresentOrElse(
            stock -> System.out.println("Stock with the highest price: " + stock),
            () -> System.out.println("No stocks available.")
        );
        System.out.println("----------------------------");

        
        System.out.println("Stocks grouped by ticker:");
        Map<String, List<Stock>> groupedByTicker = stocks.stream()
            .collect(Collectors.groupingBy(Stock::getTicker));

        groupedByTicker.forEach((ticker, stockList) -> {
            System.out.println(ticker + ":");
            stockList.forEach(System.out::println);
            System.out.println("----------------------------");
        });

       
        System.out.println("Stock count by ticker:");
        Map<String, Long> stockCountByTicker = stocks.stream()
            .collect(Collectors.groupingBy(Stock::getTicker, Collectors.counting()));

        stockCountByTicker.forEach((ticker, count) -> {
            System.out.println(ticker + ": " + count);
        });
    }
}
