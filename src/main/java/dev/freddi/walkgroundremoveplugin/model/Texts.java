package dev.freddi.walkgroundremoveplugin.model;

public class Texts {
    public class Log {
        public final static String start = "Plugin ON. 移動先の足元のブロックが消えるようになりました";
        public final static String start_already = "すでにPluginはONです";
        public final static String end = "Plugin OFF. 移動先の足元のブロックが消えないようになりました";
        public final static String end_already = "すでにPluginはOFFです";

        public final static String invalid_format = "不正なコマンドフォーマット。\n正しくは wg [ start | end ]";

        public final static String help = "\n" +
                "- wg [ start | end ] \n" +
                "  - start: \nPluginをONにしてプレイヤーの移動先の足元のブロックが消えるようにする\n" +
                "  - end: \nPluginをOFFにする";
    }
}
