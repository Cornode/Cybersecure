package com.cornode.iri.hash;

import com.cornode.iri.utils.Converter;
import com.cornode.iri.utils.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by paul on 4/15/17.
 */
public class CurlTest {
    final Random seed = new Random();
    final String trytes = "RSWWSFXPQJUBJROQBRQZWZXZJWMUBVIVMHPPTYSNW9YQIQQF9RCSJJCVZG9ZWITXNCSBBDHEEKDRBHVTWCZ9SZOOZHVBPCQNPKTWFNZAWGCZ9QDIMKRVINMIRZBPKRKQAIPGOHBTHTGYXTBJLSURDSPEOJ9UKJECUKCCPVIQQHDUYKVKISCEIEGVOQWRBAYXWGSJUTEVG9RPQLPTKYCRAJ9YNCUMDVDYDQCKRJOAPXCSUDAJGETALJINHEVNAARIPONBWXUOQUFGNOCUSSLYWKOZMZUKLNITZIFXFWQAYVJCVMDTRSHORGNSTKX9Z9DLWNHZSMNOYTU9AUCGYBVIITEPEKIXBCOFCMQPBGXYJKSHPXNUKFTXIJVYRFILAVXEWTUICZCYYPCEHNTK9SLGVL9RLAMYTAEPONCBHDXSEQZOXO9XCFUCPPMKEBR9IEJGQOPPILHFXHMIULJYXZJASQEGCQDVYFOM9ETXAGVMSCHHQLFPATWOSMZIDL9AHMSDCE9UENACG9OVFAEIPPQYBCLXDMXXA9UBJFQQBCYKETPNKHNOUKCSSYLWZDLKUARXNVKKKHNRBVSTVKQCZL9RY9BDTDTPUTFUBGRMSTOTXLWUHDMSGYRDSZLIPGQXIDMNCNBOAOI9WFUCXSRLJFIVTIPIAZUK9EDUJJ9B9YCJEZQQELLHVCWDNRH9FUXDGZRGOVXGOKORTCQQA9JXNROLETYCNLRMBGXBL9DQKMOAZCBJGWLNJLGRSTYBKLGFVRUF9QOPZVQFGMDJA9TBVGFJDBAHEVOLW9GNU9NICLCQJBOAJBAHHBZJGOFUCQMBGYQLCWNKSZPPBQMSJTJLM9GXOZHTNDLGIRCSIJAZTENQVQDHFSOQM9WVNWQQJNOPZMEISSCLOADMRNWALBBSLSWNCTOSNHNLWZBVCFIOGFPCPRKQSRGKFXGTWUSCPZSKQNLQJGKDLOXSBJMEHQPDZGSENUKWAHRNONDTBLHNAKGLOMCFYRCGMDOVANPFHMQRFCZIQHCGVORJJNYMTORDKPJPLA9LWAKAWXLIFEVLKHRKCDG9QPQCPGVKIVBENQJTJGZKFTNZHIMQISVBNLHAYSSVJKTIELGTETKPVRQXNAPWOBGQGFRMMK9UQDWJHSQMYQQTCBMVQKUVGJEAGTEQDN9TCRRAZHDPSPIYVNKPGJSJZASZQBM9WXEDWGAOQPPZFLAMZLEZGXPYSOJRWL9ZH9NOJTUKXNTCRRDO9GKULXBAVDRIZBOKJYVJUSHIX9F9O9ACYCAHUKBIEPVZWVJAJGSDQNZNWLIWVSKFJUMOYDMVUFLUXT9CEQEVRFBJVPCTJQCORM9JHLYFSMUVMFDXZFNCUFZZIKREIUIHUSHRPPOUKGFKWX9COXBAZMQBBFRFIBGEAVKBWKNTBMLPHLOUYOXPIQIZQWGOVUWQABTJT9ZZPNBABQFYRCQLXDHDEX9PULVTCQLWPTJLRSVZQEEYVBVY9KCNEZXQLEGADSTJBYOXEVGVTUFKNCNWMEDKDUMTKCMRPGKDCCBDHDVVSMPOPUBZOMZTXJSQNVVGXNPPBVSBL9WWXWQNMHRMQFEQYKWNCSW9URI9FYPT9UZMAFMMGUKFYTWPCQKVJ9DIHRJFMXRZUGI9TMTFUQHGXNBITDSORZORQIAMKY9VRYKLEHNRNFSEFBHF9KXIQAEZEJNQOENJVMWLMHI9GNZPXYUIFAJIVCLAGKUZIKTJKGNQVTXJORWIQDHUPBBPPYOUPFAABBVMMYATXERQHPECDVYGWDGXFJKOMOBXKRZD9MCQ9LGDGGGMYGUAFGMQTUHZOAPLKPNPCIKUNEMQIZOCM9COAOMZSJ9GVWZBZYXMCNALENZ9PRYMHENPWGKX9ULUIGJUJRKFJPBTTHCRZQKEAHT9DC9GSWQEGDTZFHACZMLFYDVOWZADBNMEM9XXEOMHCNJMDSUAJRQTBUWKJF9RZHK9ACGUNI9URFIHLXBXCEODONPXBSCWP9WNAEYNALKQHGULUQGAFL9LB9NBLLCACLQFGQMXRHGBTMI9YKAJKVELRWWKJAPKMSYMJTDYMZ9PJEEYIRXRMMFLRSFSHIXUL9NEJABLRUGHJFL9RASMSKOI9VCFRZ9GWTMODUUESIJBHWWHZYCLDENBFSJQPIOYC9MBGOOXSWEMLVU9L9WJXKZKVDBDMFSVHHISSSNILUMWULMVMESQUIHDGBDXROXGH9MTNFSLWJZRAPOKKRGXAAQBFPYPAAXLSTMNSNDTTJQSDQORNJS9BBGQ9KQJZYPAQ9JYQZJ9B9KQDAXUACZWRUNGMBOQLQZUHFNCKVQGORRZGAHES9PWJUKZWUJSBMNZFILBNBQQKLXITCTQDDBV9UDAOQOUPWMXTXWFWVMCXIXLRMRWMAYYQJPCEAAOFEOGZQMEDAGYGCTKUJBS9AGEXJAFHWWDZRYEN9DN9HVCMLFURISLYSWKXHJKXMHUWZXUQARMYPGKRKQMHVR9JEYXJRPNZINYNCGZHHUNHBAIJHLYZIZGGIDFWVNXZQADLEDJFTIUTQWCQSX9QNGUZXGXJYUUTFSZPQKXBA9DFRQRLTLUJENKESDGTZRGRSLTNYTITXRXRGVLWBTEWPJXZYLGHLQBAVYVOSABIVTQYQM9FIQKCBRRUEMVVTMERLWOK";
    final String hash = "KXRVLFETGUTUWBCNCC9DWO99JQTEI9YXVOZHWELSYP9SG9KN9WCKXOVTEFHFH9EFZJKFYCZKQPPBXYSGJ";

    @Test
    public void normalHashWorks() {
        int size = 8019;
        int[] in_trits = Converter.trits(trytes);
        int[] hash_trits = new int[Curl.HASH_LENGTH];
        Curl curl;
        curl = new Curl();
        curl.absorb(in_trits, 0, in_trits.length);
        curl.squeeze(hash_trits, 0, Curl.HASH_LENGTH);
        String out_trytes = Converter.trytes(hash_trits);
        Assert.assertEquals(out_trytes, hash);
    }

    @Test
    public void pairHashWorks() {
        int size = 8019;
        int[] in_trits = Converter.trits(trytes);
        Pair<long[], long[]> hashPair = new Pair<>(new long[Curl.HASH_LENGTH], new long[Curl.HASH_LENGTH]);
        Curl curl;
        curl = new Curl(true);
        curl.absorb(Converter.longPair(in_trits), 0, in_trits.length);
        curl.squeeze(hashPair, 0, Curl.HASH_LENGTH);
        int[] hash_trits = Converter.trits(hashPair.low, hashPair.hi);
        String out_trytes = Converter.trytes(hash_trits);
        Assert.assertEquals(out_trytes, hash);
    }

    @Test
    public void pairHashIsFasterThanNormalHash() {
        int size = 8019;
        long start1, diff1, start2, diff2;
        int[] in_trits = Converter.trits(trytes);
        final int[] hash_trits = new int[Curl.HASH_LENGTH];
        Curl curl, curl1;
        curl = new Curl(true);
        curl1 = new Curl();
        Pair<long[], long[]> in_pair = Converter.longPair(in_trits);
        Pair<long[], long[]> hashPair = new Pair<>(new long[Curl.HASH_LENGTH], new long[Curl.HASH_LENGTH]);
        int iteration = 0;
        while(iteration++ < 10) {
            curl.absorb(in_pair, 0, in_trits.length);
            curl.squeeze(hashPair, 0, Curl.HASH_LENGTH);
            curl.reset(true);
            curl1.absorb(in_trits, 0, in_trits.length);
            curl1.squeeze(hash_trits, 0, Curl.HASH_LENGTH);
            curl1.reset();
        }
        int serialCount = 64;
        start1 = System.nanoTime();
        while(serialCount-- > 0) {
            curl1.absorb(in_trits, 0, in_trits.length);
            curl1.squeeze(hash_trits, 0, Curl.HASH_LENGTH);
            curl1.reset();
        }
        diff1 = System.nanoTime() - start1;
        hashPair = new Pair<>(new long[Curl.HASH_LENGTH], new long[Curl.HASH_LENGTH]);
        start2 = System.nanoTime();
        curl.absorb(in_pair, 0, in_trits.length);
        curl.squeeze(hashPair, 0, Curl.HASH_LENGTH);
        diff2 = System.nanoTime() - start2;
        System.arraycopy(Converter.trits(hashPair.low, hashPair.hi), 0, hash_trits, 0, Curl.HASH_LENGTH);
        String out_trytes = Converter.trytes(hash_trits);
        Assert.assertEquals(out_trytes, hash);
        System.out.println(diff1);
        System.out.println(diff2);
        System.out.println(diff1/diff2);
        Assert.assertTrue(diff2 < diff1);
    }
}