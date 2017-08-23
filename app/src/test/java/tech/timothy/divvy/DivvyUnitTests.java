package tech.timothy.divvy;

import android.util.Log;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import tech.timothy.divvy.dataproviders.ReceiptDataProvider;
import tech.timothy.divvy.divvyutil.DivvyUtils;
import tech.timothy.divvy.factories.DivvyReceiptItemFactory;
import tech.timothy.divvy.managers.ReceiptDataManager;
import tech.timothy.divvy.receipt.Receipt;
import tech.timothy.divvy.receipt.ReceiptItem;
import tech.timothy.divvy.receipt.TaxItem;

/**
 * Created by timtan on 8/20/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class DivvyUnitTests extends TestCase {

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Log.class);
    }


    @Test
    public void testReceiptProcessor() {
        ReceiptDataManager manager = ReceiptDataManager.getInstance();
        manager.setRawData(ReceiptDataProvider.getExample());
        manager.processReceiptData();
        Receipt receipt = manager.getReceipt();
        receipt.printItems();
    }

    @Test
    public void SimilarityAlgorithm_VariousData_SimilarWhenExpected() {
        // case 1 sanity
        String str1 = "Paul Blart";
        String str2 = "Paul Bart";
        assertTrue(DivvyUtils.areStringsSimilar(str1, str2));

        // case 2 sanity
        str1 = "Jesus Christ";
        assertFalse(DivvyUtils.areStringsSimilar(str1, str2));

        // case 3 - fails when threshold high
        str2 = "Jesuz Christ";
        assertFalse(DivvyUtils.areStringsSimilar(str1, str2, 0.9));

        // case 4 - passes when threshold lowered
        assertTrue(DivvyUtils.areStringsSimilar(str1, str2, 0.7));

        // case 5 - rl scenario
        str1 = "balance";
        str2 = "baTance";
        assertTrue(DivvyUtils.areStringsSimilar(str1, str2, 0.7));
    }

    @Test
    public void ReceiptItemFactory_TaxDataInput_TaxItemCreated() {
        String lineInput = "Sales Tax 0.12";
        ReceiptItem item = DivvyReceiptItemFactory.createDivvyReceiptItem(lineInput);
        assertTrue(item instanceof TaxItem);
    }
}
