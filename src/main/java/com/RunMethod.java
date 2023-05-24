package com;

import com.parallel.leverage.api.*;
import com.parallel.leverage.api.oracle.NftPrice;
import com.parallel.leverage.entity.InputData;
import com.parallel.leverage.utils.ConfigTools;
import dnl.utils.text.table.TextTable;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * sh start.sh dev asset BAYC -token_id xxx
 * sh start.sh dev collection BAYC
 * sh start.sh dev listings  BAYC-token_id xxx
 * -listing_price xx   -valume
 * <p>
 * java -Dconf=src/main/resources/opensea.conf -jar target/parallel-leverage-test-1.0-SNAPSHOT.jar dev asset BAYC -token_id 1
 */
public class RunMethod extends ConfigTools {

    public static void main(String[] args) {
        RunMethod rm = new RunMethod();
        rm.parser(args);
        //静态初始化
        //动态初始化

    }
    public void parser(String[] args) {
        Options opts = new Options();
        opts.addOption("h", "help", false, "This is help!");
        opts.addOption("td", "token_id", true, "token_id");
        opts.addOption("p", "price", true, "listing price");
        opts.addOption("lp", "last_price", true, "last_price");
        opts.addOption("top", "top_offer", true, "top offer | best offer");
        opts.addOption("best", "best_price", true, "top offer | best offer");
        opts.addOption("ui", "ui_message", true, "collection | details");
        opts.addOption("o", "oracle", true, "oracle floor_price");
        BasicParser parser = new BasicParser();
        CommandLine cl;
        try {
            cl = parser.parse(opts, args);
            if (args.length>0||cl.getOptions().length > 0) {
                if(cl.hasOption("ui")) {
                    String ui = cl.getOptionValue("ui");
                    uiMethod(ui);
                }
                if (cl.hasOption('h')) {
                    HelpFormatter hf = new HelpFormatter();
                    hf.printHelp("Options", opts);
                } else if (cl.hasOption("td")) {
                    String td = cl.getOptionValue("td");
                    apiMethod(argsTem(args, td));
                }else if(cl.hasOption("o")) {
                    NftPrice nftPrice = new NftPrice();
                    nftPrice.getBody(new InputData());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InputData argsTem(String[] args, String tokenId) {
        InputData inputData = new InputData();
        inputData.setEnv(args[0]);
        inputData.setMethod(args[1]);
        inputData.setCollectionId(args[2]);
        inputData.setTokenId(tokenId);

        return inputData;
    }


    private void uiMethod(String method) {
        if (method.equals("collection")) {
            String[] keys = new String[4];
            keys[0] = "Items";
            keys[1] = "Volume";
            keys[2] = "Floor Price";
            keys[3] = "Buy at ParaSpace";
            String[][] value =  new String[1][4];
            value[0][0]="10k";
            value[0][1]="1m";
            value[0][2]="101";
            value[0][3]="70.70";
            TextTable tt = new TextTable(keys, value);
            tt.printTable();
        } else if (method.equals("details")) {
            System.out.println("Properties");
            String[] keys = new String[4];
            keys[0] = "Traits";
            keys[1] = "Variants";
            keys[2] = "Rarity";
            String[][] value =  new String[1][3];
            value[0][0]="Fur";
            value[0][1]="Gray";
            value[0][2]="4.96% have this trait";
            TextTable tt = new TextTable(keys, value);
            tt.printTable();
            System.out.println("Listing");
            String[] keys1 = new String[4];
            keys1[0] = "Items";
            keys1[1] = "Volume";
            keys1[2] = "Floor Price";
            keys1[3] = "Buy at ParaSpace";
            String[][] value1 =  new String[2][2];
            value1[0][0]="169";
            value1[0][1]="Opensea";
            value1[1][0]="169";
            value1[1][1]="Opensea";
            TextTable tt1 = new TextTable(keys1, value1);
            tt1.printTable();
            System.out.println("Offers");
        }
    }

    public String apiMethod(InputData inputData) {
        String method = inputData.getMethod();
        if (method.equals("asset")) {
            Asset asset = new Asset();
            asset.getBody(inputData);
        } else if (method.equals("collection")) {
            Collection collection = new Collection();
            collection.getBody(inputData);
        } else if (method.equals("listings")) {
            Listings listings = new Listings();
            listings.getBody(inputData);
        } else if (method.equals("listings_v2")) {
            ListingsV2 listings = new ListingsV2();
            listings.getBody(inputData);
        } else if (method.equals("offers")) {
            Offers offers = new Offers();
            offers.getBody(inputData);
        } else if (method.equals("offers_v2")) {
            OffersV2 offersV2 = new OffersV2();
            offersV2.getBody(inputData);
        }
        return "method is devloping……";
    }

}
