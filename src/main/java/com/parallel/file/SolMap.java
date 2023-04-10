package com.parallel.file;

import java.util.HashMap;
import java.util.Map;

public class SolMap {
    public static Map<String, String> map = new HashMap<>();

    static {
        map.put("deployer", "SignerWithAddress");
        map.put("poolAdmin", "SignerWithAddress");
        map.put("assetListingAdmin", "SignerWithAddress");
        map.put("emergencyAdmin", "SignerWithAddress");
        map.put("riskAdmin", "SignerWithAddress");
        map.put("gatewayAdmin", "SignerWithAddress");
        map.put("users", "SignerWithAddress[]");
        map.put("pool", "IPool");
        map.put("configurator", "PoolConfigurator");
        map.put("poolDataProvider", "UiPoolDataProvider");
        map.put("oracle", "PriceOracle");
        map.put("paraspaceOracle", "ParaSpaceOracle");
        map.put("protocolDataProvider", "ProtocolDataProvider");
        map.put("weth", "WETH9Mocked");
        map.put("pWETH", "PToken");
        map.put("aWETH", "MockAToken");
        map.put("paWETH", "PTokenAToken");
        map.put("dai", "MintableERC20");
        map.put("pDai", "PToken");
        map.put("variableDebtDai", "VariableDebtToken");
        map.put("stableDebtDai", "StableDebtToken");
        map.put("pUsdc", "PToken");
        map.put("usdc", "MintableERC20");
        map.put("usdt", "MintableERC20");
        map.put("nBAYC", "NToken");
        map.put("bayc", "MintableERC721");
        map.put("addressesProvider", "PoolAddressesProvider");
        map.put("registry", "PoolAddressesProviderRegistry");
        map.put("aclManager", "ACLManager");
        map.put("punk", "CryptoPunksMarket");
        map.put("wPunk", "WPunk");
        map.put("nWPunk", "NToken");
        map.put("wBTC", "MintableERC20");
        map.put("stETH", "StETH");
        map.put("pstETH", "PTokenStETH");
        map.put("ape", "MintableERC20");
        map.put("nMAYC", "NToken");
        map.put("mayc", "MintableERC721");
        map.put("nDOODLES", "NToken");
        map.put("doodles", "MintableERC721");
        map.put("mockTokenFaucet", "MockTokenFaucet");
        map.put("wPunkGateway", "WPunkGateway");
        map.put("wETHGateway", "WETHGateway");
        map.put("conduitController", "ConduitController");
        map.put("pausableZoneController", "PausableZoneController");
        map.put("conduitKey", "string");
        map.put("conduit", "Conduit");
        map.put("pausableZone", "PausableZone");
        map.put("seaport", "Seaport");
        map.put("looksRareExchange", "LooksRareExchange");
        map.put("strategyStandardSaleForFixedPrice", "StrategyStandardSaleForFixedPrice");
        map.put("transferManagerERC721", "TransferManagerERC721");
        map.put("x2y2r1", "X2Y2R1");
        map.put("erc721Delegate", "ERC721Delegate");
        map.put("moonbirds", "Moonbirds");
        map.put("nMOONBIRD", "NTokenMoonBirds");
        map.put("uniswapV3Factory", "UniswapV3Factory");
        map.put("nftPositionManager", "INonfungiblePositionManager");
        map.put("nUniswapV3", "NTokenUniswapV3");
        map.put("nftFloorOracle", "NFTFloorOracle");
    }

    public static String getValues(String key) {
        return map.get(key);
    }
}
