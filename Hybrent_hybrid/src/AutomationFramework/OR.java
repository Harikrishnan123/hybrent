package AutomationFramework;

public class OR {

	// Login Page
	public static String Login_link="LinkLogin#lnktext=Login with Hybrent";
	public static String HeaderLogo = "Logo#xpath=//img";
	public static String Label_username = "Labelusername#xpath=//*[@for='userName']";
	public static String Label_password = "Labelpassword#xpath=//*[@for='password']";
	public static String Login_Username = "Input_UserName#id=userName";
	public static String Login_Password = "Input_Password#id=password";
	public static String Login_Submit = "LoginBtn#xpath=//*[@type='submit']";
	public static String Login_Error = "Error#xpath=//h2[contains(text(),'Error?')]";
	public static String Login_Validation = "ErrorValidation#xpath=//*[contains(text(),'Invalid user name or password.')]";
	public static String Login_okay = "Errorok#xpath=//*[@class='confirm']";
	
	public static String DashBoard = "DashBoard#xpath=//a[@href='#/dashboard']";



	// Shop
	public static String Shop_Menu= "ShopMenu#xpath=//*[@href='#/shop']";
	public static String Shop_wait= "Shopwait#xpath=//*[@id='ng-view']";
	public static String Shop_Shopfor = "ShopFor#xpath=//*[contains(text(),'Shopping for')]";
	public static String Shop_SHopfor_ShopfaclitySelect = "SelectShopfaclity#xpath=//*[@title='Select Facility']";
	public static String Shop_SHopfor_getfacilityName = "getfacilityName#xpath=//*[@ng-click='selectItem();']";
	public static String Shop_SHopfor_allfacilityArray = "allfacilityArray#xpath=//td";
	public static String Shop_SHopfor_selectedFAcility = "selectedFAcility#xpath=//*[@disabled='disabled']";
	public static String Shop_SHopfor_cancelButtonPopup = "cancelButtonPopup#xpath=//*[@ng-click='cancel()']";
	public static String Shop_SHopfor_Shopfaclity = "Shopfaclity#xpath=//*[text()='Select Facility']";

	public static String Shop_SHopfor_SearchMenu_SKU = "SearchMenu_SKU#xpath=//*[contains(@ng-click, 'setSelectedItem(getPropertyForObject(option,settings.idProp))') and contains(text(),'SKU')]";
	public static String Shop_SHopfor_SearchBox= "SearchBox#xpath=//*[@id='search']";
	public static String Shop_SHopfor_Search_itemdesc= "Search_itemdesc#xpath=//item-info";
	//    public static String Shop_SHopfor_Search_vendorName= "Search_vendorName#xpath=//vendor-info/span";
	public static String Shop_SHopfor_Search_Match= "Search_Match#xpath=//*[@id='matchselect']";
	public static String Shop_SHopfor_Search_skuName="skuName#xpath=//*[@class='table table-striped shopTable']//tr[1]//span";
	public static String Shop_SHopfor_SelectfirstItemvendorName= "SelectvendorName#xpath=(//vendor-info)[1]/span";
	public static String Shop_SHopfor_clickvendorsinDropdown= "clickvendorsinDropdown#xpath=//*[@id='vendor']";
	public static String Shop_SHopfor_allvendorsinDropdown= "allvendorsinDropdown#xpath=//*[@id='vendor']/option";

	public static String Shop_SHopfor_MyInventoryradiobutton="MyInventoryradiobutton#xpath=//*[@id='searchShop']/div[1]";
	public static String Shop_SHopfor_RecentlyOrderedradiobutton="RecentlyOrderedradiobutton#xpath=//*[@id='searchShop']/div[2]";
	public static String Shop_SHopfor_MostOrderedradiobutton="MostOrderedradiobutton#xpath=//*[@id='searchShop']/div[3]";
	public static String Shop_SHopfor_FavOrderedradiobutton="FavOrderedradiobutton#xpath=//*[@id='searchShop']/div[4]";
	public static String Shop_selectExactMatch= "selectExactMatch#xpath=//*[@id='matchselect']";
	public static String Shop_SHopfor_ResultGridTab="ResultGridTab#xpath=//*[@id='ng-view']/div/div[2]/div[3]/grid-layout/div[2]/div/div/div/div/table/thead/tr/th[7]/a";

	public static String Shop_SHopfor_LastorderedText="LastorderedText#xpath=//th[@class='ng-scope text-left']/a";
	public static String Shop_SHopfor_PoCountText="PoCountText#xpath=//th[@class='ng-scope text-right'][1]/a";



	public static String Shop_SHopfor_favtab="favtab#xpath=//*[@ng-if=\"isShowFavorite == 'true'\"]/i";
	public static String Shop_SHopfor_myInventoryFavTab="myInventoryFavTab#xpath=//i[@ng-if='!isFavorite']";  

	public static String Shop_orgPage="orgPage#xpath=//*[@href='#/admin/organization']";
	public static String Shop_FeaturesPage="FeaturesPage#xpath=//ul[@class='nav nav-tabs']/li[2]/a";  	

	public static String Shop_SHopfor_drilldownicon="drilldownicon#xpath=//button[@ng-readonly='moveingToPunchOutSite']";
	public static String Shop_SHopfor_showTourText="showTourText#xpath=//ul[@ng-readonly='moveingToPunchOutSite']/li[1]/a";

	public static String Shop_SHopfor_createNewLAyout="createNewLAyout#xpath=//li[@ng-click='newShopTableGrid(false);]";
	public static String Shop_SHopfor_Layoutpopup="Layoutpoup#xpath=//div[@class='headtext']";
	public static String Shop_SHopfor_Layoutpoupclose="Layoutpoupclose#xpath=//*[@class='close pull-right']";

	public static String Shop_SHopfor_copyLayoutpoup="copyLayoutpoup#xpath=//a[contains(text(),'Copy Current Layout')]";

	public static String Shop_SHopfor_showtourtextONPOPUP="showtourtext#xpath=//h3[contains(text(),'Shop: Search For')]";
	public static String Shop_SHopfor_showtourNextButtonText="showtourNextButtonText#xpath=//button[contains(text(),'Next �')]";
	//public static String Shop_SHopfor_showtourENDbuttonTExt="showtourENDbuttonTExt#xpath=//button[contains(text(),'End tour')]";
	public static String Shop_SHopfor_showtourENDbuttonTExt="showtourENDbuttonTExt#xpath=(//button[@ng-click='tour.end();'])[1]";

	public static String Shop_SHopfor_allFieldsBtn="allFieldsBtn#xpath=//div[@options='searchKeysList']/div/button";

	public static String Shop_SHopfor_Search_vendorName_Price= "Search_vendorName_price#xpath=//*[@current-price='item.purchase_price']/div";
	public static String Shop_SHopfor_Search_Addtocart= "Search_AddtocartBtn#xpath=//*[@ng-click='$ctrl.addItemToCart(item)']";


	public static String Shop_GrandTotal = "GrandTotal#xpath=//*[@tour-step='cart-grand-total']/td";
	public static String Shop_GeneratePo = "GeneratePo#xpath=(//*[@id='btnAdd'])[1]";

	public static String Shop_Allfieldsbutton = "Shop_Allfieldsbutton#xpath=//*[@ng-click='toggleDropdown()']";
	public static String Shop_waitdropdown = "Shop_waitdropdown#xpath=//*[@role='menuitem']";
	public static String Shop_orgpage = "Shop_Shop_orgpage#xpath=//*[@ng-href='#/admin/organization']";
	public static String Shop_orgFeaturesPage = "Shop_orgFeaturesPage#xpath=//ul[@class='nav nav-tabs']";
	public static String Shop_orgFeatures2Page = "Shop_orgFeaturesPage#xpath=//a[text()='Features']";
	public static String Shop_crosswalktogglevalue ="Shop_crosswalktogglevalue#xpath=//*[@class='col-sm-8 user-action-switch']/div";
	public static String Shop_crosswalktoggleON ="Shop_crosswalktoggleON#xpath=";
	public static String Shop_dropDownText ="Shop_dropDownText#xpath=//*[@ng-show='settings.selectionLimit > 1']/a ";
	public static String Shop_searchfield= "searchfield#xpath=//*[@id='search']";
	public static String Shop_ifnoItemfield= "ifnoItemfield#xpath=//tr[@ng-if='!items.length']/td";
	//public static String Shop_wait= "ifnoItemfield#xpath=//tr[@ng-if='!items.length']/td";

	public static String Shop_showTour= "showTour#xpath=//li[@ng-click='startTour();']";
	public static String Shop_mouseoverviewtype= "mouseoverviewtype#xpath=//li[@class='dropdown-submenu pull-left']";

	public static String Shop_waitfor = "mouseoverviewtype#xpath=//ul[@class='dropdown-menu dropdown-menu-less-padding dropdown-menu-round-bordered']";
	public static String Shop_tableviewText = "tableviewText#xpath=//ul[@class='dropdown-menu dropdown-menu-less-padding dropdown-menu-round-bordered']/li[1]";
	public static String Shop_tableviewIdentifier = "tableviewIdentifier#xpath=//*[@id='isotopeContainer']";
	public static String Shop_tableview = "tableview#xpath=//*[contains(text(),'Table')]";

	//public static String Shop_gridViewText = "gridViewText#xpath=//ul[@class='dropdown-menu dropdown-menu-less-padding dropdown-menu-round-bordered']/li[2]";
	public static String Shop_gridView = "gridView#xpath=//*[contains(text(),'Grid')]";
	public static String Shop_layoutshover = "layoutshover#xpath=//*[@class='dropdown-submenu pull-left ng-scope']";
	public static String Shop_userlayout = "layoutshover#xpath=//li[@class='dropdown-submenu pull-left ng-scope']/ul/";

	public static String Shop_selectmatchdropdown = "selectmatchdropdown#xpath=//*[@id='matchselect']";
	public static String Shop_elementwithcross ="elementwithcross#xpath=//*[@ng-if='currentUser.user.id == layout.created_by])";
	public static String Shop_waitforelements ="Shop_waitforelements#xpath=//*[@class='col-sm-18 ng-scope']";
	public static String Shop_countoffacilities ="Shop_countoffacilities#xpath=//*[@style='border-right: none;vertical-align: middle;']";
	public static String Shop_countofitems ="Shop_countofitems #xpath=//tr[@ng-repeat='data in $ctrl.selectList | orderBy:'name' | filter: { name:search.searchKeyword } | offset: (search.currentPage - 1) * search.iPerPage | limitTo: search.iPerPage']/span";
	public static String Shop_VendorSelect = "VendorSelect#xpath=//*[@id='vendor']";	 
	//Manage Inventory
	public static String manageInventory_waitforelements ="waitforelements#xpath=//*[@ng-repeat='item in items']";

	public static String Shop_drilldownCreateLayout = "drilldownCreateLayout#xpath=//a[text()='Create New Layout']";
	public static String Shop_drilldownChangeLayout = "drilldownChangeLayout#xpath=//ul[@class='dropdown-menu dropdown-menu-right dropdown-menu-less-padding']//a[text()='Change Current Layout']";
	public static String Shop_drilldownLayoutNameText = "drilldownLayoutNameText#xpath=//label[text()='Layout Name']/../div/input";
	public static String Shop_drilldownCreateLayoutSave = "drilldownCreateLayoutSave#xpath=//*[@class='modal-footer']/button[text()='Save']";
	public static String Shop_drilldownChangeLayoutPopup = "drilldownChangeLayoutPopup#xpath=//span[text()='Configure Shop Layout ---automation layout---']";
	public static String Shop_drilldownChangeLayoutClose = "drilldownChangeLayoutClose#xpath=//button[@class='close']";
	//public static String Shop_drilldownRemoveLayout = "drilldownRemoveLayout#xpath=//a[contains(text(),'"+layout+"')]/../i";
	public static String Shop_drilldownLayouts = "drilldownLayouts#xpath=//a[text()='Layouts']";
	public static String Shop_drilldownRemoveLayoutYes = "drilldownRemoveLayoutYes#xpath=//button[@class='confirm']";
	public static String Shop_changeLayoutTextPopUP= "changeLayoutTextPopUP=#xpath=//*[@class='headtext']/span";
	public static String Shop_wait2= "Shop_wait=#xpath=//*[@id='ng-view']";
	
	//*[@ng-repeat='item in items']

	// Dashboard Page
	public static String DashBoard_AdminDropdown = "DropDown_Admin#xpath=//admin-menu/a";
	public static String DashBoard_Report_Montly = "Report_Month#xpath=//*[@class='current-day-year ng-binding']";
	public static String DashBoard_Report_Monthly_Dolors = "Report_Montly_Dolors#xpath=(//*[@class='current-day-temperature ng-binding'])[1]";
	public static String DashBoard_Report_Monthly_txt = "Report_Monthly_txt#xpath=(//*[@class='current-day-date']/span)[1]";
	public static String DashBoard_Report_PartialReviews = "PartialReviews#xpath=(//*[@class='current-day-temperature ng-binding'])[2]";
	public static String DashBoard_Report_PartialReviews_txt = "PartialReviews_txt#xpath=(//*[@class='current-day-date']/span)[2]";
	public static String DashBoard_Report_Day = "Day#xpath=(//*[@class='current-days ng-binding'])[1]";
	public static String DashBoard_Report_Day_txt = "DayText#xpath=(//*[@class='current-days_2'])[1]";
	public static String DashBoard_Report_Hours = "Hours#xpath=(//*[@class='current-days ng-binding'])[2]";
	public static String DashBoard_Report_Hours_txt = "hoursText#xpath=(//*[@class='current-days_2'])[2]";
	public static String DashBoard_Report_avgventor_txt = "avgventorText#xpath=(//*[@class='current-day-date']/span)[3]";
	public static String DashBoard_Report_RecentOrder_txt = "Txt_recentorder#xpath=//*[text()='Recent Orders']";
	public static String DashBoard_Report_NewsEvent_txt = "Txt_NewsEvent#xpath=//*[text()='News & Events']";
	public static String DashBoard_Report_NewsDate = "NewsDate#xpath=//*[@class='newsicon in-active']";
	public static String DashBoard_Report_NewsTime = "NewsTime#xpath=//*[@class='news-time ng-binding']";
	public static String DashBoard_Report_News = "News#xpath=(//*[@class='news-sub-teaser'])[1]";
	public static String DashBoard_Report_News_Header = "NewsHeader#xpath=//*[@class='pagehead']";
	public static String DashBoard_Report_Newstitle1 = "NewsTitle#xpath=(//*[@class='news-sub-head ng-binding'])[1]";
	public static String DashBoard_Report_NewsPage_title1 = "NewsTitle1#xpath=(//h5)[1]";
	public static String Admin_ItemCatalog = "ItemCatalog#xpath=//a[@href='#/items-catalog']";

	// Item Catalog
	public static String Shop_ItemNameDropDown_First = "ItemNameDropDown#xpath=(//*[@id='optionMenu']//*[@class='fa fa-chevron-down'])[1]";																																							 
	public static String Shop_ItemNameDropDownPricechangeHistory_First = "ItemNameDropDownPriceHistory#xpath=//body/ul[@class='dropdown-menu']//*[text()='Price Change History']";
	public static String Shop_ItemNameDropDownPurchaceHistory_First = "ItemNameDropDownPurchaceHistory#xpath=//body/ul[@class='dropdown-menu']//*[text()='Purchase History']";
	public static String Shop_ItemNameDropDownEdit_First = "ItemNameDropDownEdit#xpath=//body/ul[@class='dropdown-menu']//a[text()='Edit']";
    	
	public static String Shop_Match_Text = "Match_Text#xpath=//*[@for='matchselect']";
	public static String Shop_Item_Qty_First = "Qty#xpath=(//*[@ng-click='item.qty=getCartItem(item.item_vendor_id).qty; textBtnForm.$show();'])[1]";										
    public static String Shop_Item_Qty_Increase_First = "Qty_Increase#xpath=(//*[@ng-click='incrementQty(getCartItem(item.item_vendor_id).id)'])[1]";										
    public static String Shop_Item_Qty_Derease_First = "Qty_Degrease#xpath=(//*[@ng-click='decrementQty(getCartItem(item.item_vendor_id).id)'])[1]";										
    public static String Shop_SHopfor_Search_Addtocart_First= "Search_AddtocartBtn#xpath=(//*[@ng-click='$ctrl.addItemToCart(item)'])[1]";
  

	public static String ItemCatalog_statusDropdown="statusDropdown#xpath=//*[@id='status']";
	public static String ItemCatalog_searchbutton="searchbutton#xpath=//*[@class='btn btn-primary' and text()='Search']";
	public static String ItemCatalog_waitforeditbuttonclass="waitforeditbuttonclass#xpath=//*[@class='btn btn-primary ng-scope']"; 
	public static String ItemCatalog_inactiveitem="inactiveitem#xpath=//*@id='itemmasterListing']/tr/span";
	public static String ItemCatalog_AddPage_AddItemText = "AddItemText#xpath=//*[text()='Add Item']";
	public static String ItemCatalog_AddPage_ItemDetails = "Txt_ItemDetails#xpath=//*[text()='Item Detail']";
	public static String ItemCatalog_AddPage_temText = "AddItemText#xpath=//*[text()='Item Detail']";
	public static String ItemCatalog_AddItem = "Btn_AddItem#xpath=//button[contains(text(),'Add Item')]";
	public static String ItemCatalog_firstItemDesc ="firstItemDesc#xpath=(//*[@class='item-description']/span)[1]";
	public static String ItemCatalog_VendorTabDrillDownIcon ="VendorTabDrillDownIcon#xpath=//*[@class='btn-group']";
	//public static String ItemCatalog_CWIDLink ="CWIDLink#xpath=//*[contains,(text(),'Add CWID')]";
	public static String ItemCatalog_MApFac_UpdatePriceButton="MApFac_UpdatePriceButton#xpath=(//*[@ng-click='manageItemVendorFacility(vendor)'])[1]";
	public static String ItemCatalog_headTextEDit_Update = "headTextEDit_Update#xpath=//*[@class='modal-header']";	
	public static String ItemCatalog_EditItem ="EditItem#xpath=(//*[@ng-click='addEditItem(item.id)'])[1]";
	public static String ItemCatalog_firstItemSku ="firstItemSku#xpath=//*[@class='vendor_info']//span[@class='text-with-tooltip ng-binding']";
	public static String ItemCatalog_firstItemMfr ="firstItemMfr#xpath=//*[@class='vendor_info ng-scope']//span[@class='ng-binding']";
	public static String ItemCatalog_wait="wait#xpath=//*[@id='itemmasterListing']";
	public static String ItemCatalog_AddItem_ItemDetails_Description = "ItemDetails_Description#xpath=//*[@id='desc']";
	public static String ItemCatalog_AddItem_ItemDetails_alias = "ItemDetails_alias#xpath=//*[@id='alias']";	
	public static String ItemCatalog_AddItem_ItemDetails_MfrDetails = "ItemDetails_MfrDetails#xpath=//*[@id='mfr']";
	public static String ItemCatalog_AddItem_ItemDetails_MfrDetailsDropdown = "ItemDetails_MfrDropdown#xpath=//*[@class='input-group-btn']/button";
	public static String ItemCatalog_AddItem_Man_Select = "ManSelect#xpath=(//*[@ng-click='save(manufacturer.id, manufacturer.name)'])[1]";
	public static String ItemCatalog_AddItem_Man_Save = "Save#xpath=//*[@type='submit']";
	public static String ItemCatalog_AddItme_TestId = "TestId#xpath=//*[@id='item_identifier']";
	public static String ItemCatalog_Vendors = "Vendors#xpath=//a[contains(text(),'Vendors')]";
	public static String ItemCatalog_VendorsTab = "VendorsTab#lnktext=Vendors";
	public static String ItemCatalog_gpo = "gpo#xpath=//*[@class='v.facilityItemVendors.gpo_contract_price']";
	public static String ItemCatalog_AddVendors = "AddVendors#xpath=//input[@value='Add Vendor']";
	public static String ItemCatalog_Add_Vendorsname = "Vendorsname#xpath=//*[@id='tbl_add_vendor1']/tbody/tr[1]/td[2]/div[1]/div/span/span[1]";
	public static String ItemCatalog_Add_VendorsnameSelect = "VendorsnameSelect#xpath=//table[@id='tbl_add_vendor1']/tbody/tr/td[4]/select";
	public static String ItemCatalog_firstvendor = "firstvendor#xpath=//*[@id='tbl_add_vendor1']/tbody/tr[1]/td[2]/div[1]/div/span/span[2]/span";
	public static String ItemCatalog_itemCategory = "itemCategory#xpath=//select[@ng-model='item.category_id']";
	public static String ItemCatalog_itemalias = "itemalias#xpath=//*[@id='alias']";
	public static String ItemCatalog_itemCategoryfirstItem = "itemCategoryfirstItem#xpath=//select[@ng-model='item.category_id']/option[2]";
	public static String ItemCatalog_searchTextBox = "searchTextBox#xpath=//*[@id='PONUM']";
	public static String ItemCatalog_searchButtoncommon = "searchButtoncommon#xpath=//button[text()='Search']";
	public static String ItemCatalog_firstEDitButton = "firstEDitButton#xpath=(//*[@ng-click='addEditItem(item.id)'])[1]";
	public static String ItemCatalog_firstitemName = "firstitemName#xpath=(//*[@class='item-description'])[1]";
	
	// public static String ItemCatalog_Add_allvendorsfromdropdown = "VendorsnameSelect#xpath=//table[@id='tbl_add_vendor1']/tbody/tr/td[4]/select";
	public static String ItemCatalog_Add_VendorsSkuName ="VendorsSkuName#xpath=//input[@ng-model='v.sku']";
	public static String ItemCatalog_Add_VendorsMinOrderQty = "VendorsMinOrderQty#xpath=//input[@ng-model='v.min_order_qty']";
	public static String ItemCatalog_AddItem_MapValidation = "MapValidation#xpath=//*[contains(text(),'Do you want to map this item to your various facilities?')]";
	public static String ItemCatalog_AddItem_MapValidation_Cancel = "MapValidation_Cancel#xpath=//*[@class='cancel']";
	public static String ItemCatalog_AddItem_MapValidation_yes = "MapValidation_Yes#xpath=//*[@class='confirm']";
	public static String ItemCatalog_AddItem_VendorSelect_First = "VendorSelect_First#xpath=(//*[@ng-bind-html='vendor.name | highlight: $select.search'])[1]";
	public static String ItemCatalog_SaveButton ="SaveButton#xpath=//*[@type='submit']";
	public static String ItemCatalog_mapallbuttontopright ="mapallbuttontopright#xpath=//*[contains(text(),'More Options')]";
	public static String ItemCatalog_mapwithalltopright ="mapwithalltopright#xpath=//a[text()='Map with all facility']";
	public static String ItemCatalog_verifytextpopup ="verifytextpopup #xpath= //*[@class='modal-header']/div/span";
	public static String ItemCatalog_purchaseprice ="purchaseprice#xpath= //input[@ng-model='purchase_price']";
	//public static String ItemCatalog_mapallbutton ="mapallbutton#xpath=//button[@type='submit']";
	public static String ItemCatalog_mapallbutton ="mapallbutton#xpath=//button[@id='btnAdd']";
	// Add item Map faility
	public static String ItemCatalog_AddItem_Map_Header = "Map_Header#xpath=//*[@class='headtext']";
	public static String ItemCatalog_AddItem_Map_SearchFacility_Text = "SearchFacilityText#xpath=//*[text()='Search Facility']";
	public static String ItemCatalog_AddItem_Map_SearchFacility ="SearchFacility#xpath=//*[@id='facility-search']";
	public static String ItemCatalog_AddItem_Map_AddFacility ="AddFacility#xpath=//*[@id='tblMapFacility']/tbody/tr[2]/td[2]/button";
	public static String ItemCatalog_AddItem_Map_PurchagePrice ="PurchagePrice#xpath=//*[@id='purchase-price0']";
	public static String ItemCatalog_AddItem_Map_GPOPurchagePrice ="GPOPurchagePrice#xpath=//*[@name='gpo_contract_price']";
	public static String ItemCatalog_AddItem_Map_GPOPurchagePriceSave ="GPOPurchagePriceSave#xpath=(//button[contains(text(),'Save')])[1]";
	public static String ItemCatalog_AddItem_Map_Closepage ="Close#xpath=//*[@class=\"headtext\"]//button[@class='close']";

	// Notification
	public static String Notification_Bell = "Bell#xpath=//*[@id='notification-count']";
	public static String Notification_First = "FirstNotification#xpath=(//*[@class='notifiy-detail ng-binding'])[1]";
	public static String Notification_First_Time = "FirstNotificationTime#xpath=(//*[@class='notifiy-time ng-binding'])[1]";
	public static String Notification_ViewAll = "ViewAll#xpath=//*[@id='all-notifications']/a";
	public static String Notification_Mynotification = "Mynotification#xpath=//*[contains(text(),'My NOTIFICATIONS')]";
	public static String Notification_Btn_Refresh = "BtnRefresh#xpath=//*[text()='Refresh']";
	public static String NotficationPage_Text = "txt_Notification#xpath=//div[contains(text(),'Notification Settings')]";
	public static String NotficationPage_Text_Event = "txt_Event#xpath=//*[text()='Event']";
	public static String NotficationPage_Text_MobilePush = "txt_MobilePush#xpath=//*[text()='Mobile Push']";
	public static String NotficationPage_Text_Email = "txt_Email#xpath=//*[text()='Email Notification']";
	public static String NotficationPage_Text_Web = "txt_Web#xpath=//*[text()='Web Notification']";
	public static String NotficationPage_FirstNotificationText = "FirstNotification#xpath=(//*[@id='gridNotificationListing']//p)[1]";


	// User Setting
	public static String User = "User#xpath=//*[@class='user-cont']";
	public static String User_Profile = "Profile#xpath=//a[@href='#/user/update-profile']";
	public static String User_NotificationSetting = "NotificationSetting#xpath=//*[@href='#/notification/settings']";
	public static String User_alert = "alert#xpath=//*[@href='#/user/update-profile/alert']";
	public static String User_Survey = "Survey#xpath=//a[text()='Survey']";
	public static String User_ChangePassword = "ChangePassword#xpath=//a[text()='Change Password']";
	public static String User_SwitchUser = "SwitchUser#xpath=//*[@href='#/selectuser']";

	// My Cart
	public static String MyCart = "MyCart#xpath=//*[@href='#/my-cart']";
	public static String MyCart_cartFor = "text_cartFor#xpath=//*[contains(text(),'Cart for')]";
	public static String MyCart_cartFor_Details = "text_cartFor_Details#xpath=//*[@select-list='shippingLocations']/a";
	public static String MyCart_searchInCart = "searchInCart#xpath=//*[@id='searchInput']";
	public static String MyCart_addItemInCart = "addItemInCart#xpath=//button[@id='btnAdd']";
	public static String MyCart_warningPopup = "warningPopup#xpath=//*[@class='row']";
	public static String MyCart_continueButton = "continueButton#xpath=//button[text()='Continue']";
	public static String MyCart_firstItemName = "firstItemName#xpath=//ul[@id='suggestions']/li/div/div[1]/div/span/strong";
	public static String MyCart_firstItemNamewait = "firstItemNamewait#xpath=//ul[@id='suggestions']";
	public static String MyCart_firstItemSkuName = "firstItemSkuName#xpath=//*[@id='suggestions']/li[1]/div/div[3]/div[1]/span/small";	
	public static String MyCart_drillDown = "drillDown#xpath=//button[@class='btn btn-default dropdown-toggle' and @type='button']";

	public static String MyCart_drillDownDiv = "drillDownDiv#xpath=//*[@class='btn-group pull-right ng-scope' and @role='group']";

	public static String MyCart_clearCurrentCart = "clearCurrentCart#xpath=//a[contains(text(),'Clear Current Cart')]";
	public static String MyCart_yesInPopup = "MyCart_yesInPopup#xpath=//button[contains(text(),'Yes')]";
	public static String MyCart_OKInPopup = "OKInPopup#xpath=//button[contains(text(),'OK')]";
	public static String MyCart_clearAllCarts = "clearAllCart#xpath=//a[contains(text(),'Clear Current Cart')]";
	//public static String MyCart_qtyOfItemBeforeAfter = "qtyOfItemBeforeAfter#xpath=//*[@id='ng-view']/div/div/div[3]/div/div/div/table/tbody[1]/tr[3]/td[3]/div/span";
	public static String MyCart_qtyOfItemBeforeAfter = "qtyOfItemBeforeAfter#xpath=//*[@class='fix_qty pull-right']//big";
	public static String MyCart_plusIcon = "plusIcon#xpath=//button[@id='incr']";
	public static String MyCart_minusIcon = "minusIcon#xpath=//button[@id='dcr']";
	public static String MyCart_editItemQty = "editItemQty#xpath=//*[@editable-text='cartItem.newQty']//*[@class='item-qty-editable-label ng-binding']";
	public static String MyCart_editItemQtyTextBox = "editItemQtyTextBox#xpath=//div[@class='editable-controls form-group']/input";      
	public static String MyCart_submitQty = "submitQty#xpath=//*[@id='ng-view']/div/div/div[3]/div/div/div/table/tbody[1]/tr[3]/td[3]/form/div/span/button[1]";
	public static String MyCart_SiTextBox = "SiTextBox#xpath=//input[@class='cart-comment-box-without-value text-primary ng-pristine ng-untouched ng-valid']";
	public static String MyCart_UsemyPOCheckBox = "UsemyPOCheckBox#xpath=//*[@ng-click='test_check(key)']";
	public static String MyCart_typePONumber = "typePONumber #xpath=//*[@ng-show='checked_pos[key].show']//*[@class='form-control ng-pristine ng-valid ng-valid-maxlength ng-touched input-sm ng-untouched ng-empty']";

	public static String MyCart_drillDownVendor = "drillDownVendor#xpath=//span[@id='optionMenu']";
	public static String MyCart_removeVendor = "removeVendor#xpath=//*[contains(text(), 'Remove Vendor from Cart')]";   
	public static String MyCart_confirmButton = "confirmButton#xpath=//button[@class='confirm']";
	public static String MyCart_accountSetUp = "MyCart_accountSetUp#xpath=//*[contains(text(), 'Account setup')]"; 
	public static String MyCart_emptycartText = "MyCart_emptycartText#xpath=//*[@class='table']/tbody/tr/td/h3";
	public static String MyCart_vendorAccountSetUp = "vendorAccountSetUp#xpath=//*[@class='vendor-acc-header ng-binding']";

	public static String MyCart_AddDepartmenthyperLink = "AddDepartmenthyperLink#xpath=//a[@ng-click='selectDepartments(cartItem);']";
	public static String MyCart_AddDepartmentText = "AddDepartmentText#xpath=(//h4)[1]";
	public static String MyCart_AddDepartmentPopUPcancel = "_AddDepartmentPopUPcancel #xpath=//*[@class='close']";
	public static String MyCart_AddPhysicianhyperLink = "AddPhysicianhyperLink#xpath=//a[@ng-click='selectPhysicians(cartItem);']";
	public static String MyCart_AddPhysicianText = "AddPhysicianText#xpath=(//h4)[1]";
	public static String MyCart_PrintPO= "PrintPO#xpath=//a[@ng-click='showPopUp()']";
	public static String MyCart_PrintPOPopUpText= "PrintPOPopUpText#xpath=//*[@class='modal-content ng-scope']/div/div/span";
	public static String MyCart_PrintCloseclose="PrintPReviewclose#xpath=//*[@class='fa fa-2x fa-times']";
	public static String MyCart_showTourButton= "showTourButton#xpath=//a[@ng-click='startTour();']";
	public static String MyCart_showtourtextONPOPUP="showtourtextONPOPUP#xpath=//h3[contains(text(),'Cart: List of Items')]";
	public static String MyCart_cartIconNumber="cartIconNumber#xpath=//*[@href='#/my-cart']/i/sup";
	public static String MyCart_clearAllCarts2 = "clearAllCarts#xpath=//*[@ng-click='clearCart()']";	

	// Profile
	public static String Profile_UserProdile = "UserProfile#xpath=//span[text()='User Profile']";
	public static String Profile_Btn_Update = "Btn_Update#xpath=//button[text()='Update']";
	public static String Profile_Btn_cancel = "Btn_Cancel#xpath=//*[@value='Cancel']";
	public static String Profile_Label_Name = "Label_Name#xpath=//*[@for='name']";
	public static String Profile_Label_Email = "Label_email#xpath=//*[@for='email']";
	public static String Profile_Label_phone = "Label_phone#xpath=//*[@for='phone']";
	public static String Profile_Label_Photo = "Label_Photo#xpath=//*[@for='pic']";
	public static String Profile_Label_Facility = "Label_Facility#xpath=//label[text()='Facility']";
	public static String Profile_Label_Dept = "Label_Dept#xpath=//label[text()='Department:']";
	public static String Profile_Label_Inventory = "Label_Inventory#xpath=//label[text()='Inventory']";
	public static String Profile_Label_Journeydate = "Label_Journeydate#xpath=//label[text()='Joining Date:']";
	public static String Profile_Label_QuickBook = "Label_QuickBook#xpath=//label[text()='Quick Books:']";
	public static String Profile_userEmail="userEmail#xpath=//*[@id='email']";

	// Alert
	public static String Alert_txt_userAlert = "txt_userAlert#xpath=//span[text()='User Alert']";
	public static String Alert_Btn_Update = "Btn_Update#xpath=//button[text()='Update']";
	public static String Alert_Btn_cancel = "Btn_Cancel#xpath=//*[@value='Cancel']";

	// Survey
	public static String Survey_txt_Survey = "txt_survey#xpath=//*[@class='headtext']/span";
	public static String Survey_btn_Clickhere = "btn_Clickher#xpath=//button[text()='Click here to start!']";

	// change password
	public static String Cpwd_txt_changepassword = "txt_changepassword#xpath=//div[contains(text(),'Change Password')]";
	public static String Cpwd_txt_oldpassword = "txt_oldpassword#xpath=//*[@for='oldPassword']";
	public static String Cpwd_txt_newpassword = "txt_newpassword#xpath=//*[@for='newPassword']";
	public static String Cpwd_txt_confirmpassword = "txt_confirmpassword#xpath=//*[@for='confirmPassword']";
	public static String Cpwd_btn_submit = "Btn_Submit#xpath=//*[@type='submit']";

	// Switch User
	public static String SwitchUser_txt_Msg = "Selectuser#xpath=//*[text()='Select user to login']";

	// Report
	public static String ViewMoreReport = "ViewReport#xpath=//*[contains(text(),'View More Reports')]";

	//manage Inventory
	public static String manageInvenLink = "manageInvenLink#xpath=//a[@href='#/inventory']";
	public static String manage_stsusDropdwn = "manage_stsusDropdwn#xpath=//*[@id='status']";
	public static String manage_searchButton = "searchButton#xpath=//button[text()='Search']";
	public static String manage_inactiveitem= "inactiveitem#xpath=//*[@class='item-description']/span";
	public static String manage_wait= "wait#xpath=//*[@id='itemmasterListing']";
	//Request PO
	public static String Request_MenuLink = "MenuLink#xpath=//a[@href='#/service-request/create']";
	public static String Request_pageDoesnotExist="pageDoesnotExist#xpath=//*[@class='sweet-alert showSweetAlert visible']//h2";
	public static String Request_drillDown = "Request_drillDown#xpath=//*[@class='pagehead']//*[@data-toggle='dropdown']";
	public static String Request_createNewService = "createNewService#xpath=//*[contains(text(),'Add New Service')]";
	public static String Request_createNewServicePopUpText = "createNewServicePopUpText#xpath=//div[@class='modal-header']/div/span";
	public static String Request_cancelPopUP = "cancelPopUP#xpath=//*[@class='fa fa-2x fa-times']";
	public static String Request_ServiceLocationText="ServiceLocationText#xpath=//label[@for='shippingLocation']";
	public static String Request_ServiceDateText="ServiceDateText#xpath=//label[@for='txtExpectedDate']";
	public static String Request_CalenderIcon="CalenderIcon#xpath=//*[@class='input-group-btn']//button[@class='btn btn-default btn-sm']";
	public static String Request_CalenderDate="CalenderDate#xpath=//button[@id='datepicker-26501-1837-title']";
	public static String Request_VendorText="VendorText#xpath=//label[@for='vendor']";
	public static String Request_Description="Description#xpath=//*[@id='item_description']";
	public static String Request_sku="sku#xpath=//*[@id='sku']";
	public static String Request_purchasePrice="purchasePrice#xpath=//*[@name='purchasePrice']";
	public static String Request_saveButton="saveButton#xpath=name=//*[@ng-click='addItem()']";
	public static String Request_searchBox="searchBox#xpath=//input[@id='searchInput']";
	public static String Request_requestService="requestService#xpath=//button[@id='btnAdd']";
	//public static String Request_isServicePOToggle="isServicePOToggle#xpath=//div[@class='bootstrap-switch-small bootstrap-switch-id-isService bootstrap-switch bootstrap-switch-wrapper bootstrap-switch-animate bootstrap-switch-off']/div//span[@class='bootstrap-switch-handle-off bootstrap-switch-default']";
	public static String Request_isServicePOToggle="isServicePOToggle#xpath=//input[@id='isService']/../span[3]";


	public static String Request_getItemName="getItemName#xpath=//div[@class='col-sm-18']/span/strong";
	public static String Request_getSkuName="getSkuName#xpath=//div[@class='col-sm-7']/span/small";
	public static String Request_vendorDropDown="vendorDropDown#xpath=//*[@id='vendor']";
	public static String Request_calenderIconAttribte="calenderIconAttribte#xpath=//button[@ng-click='open($event)']";
	public static String Request_Duration="Duration#xpath=//*[@ng-show='item.is_service==1']/select";

	//ORDERS

	public static String Orders_Link="OrdersLink#xpath=//*[@href='#/orders']";
	public static String Order_PO_Doc_Icon ="PO_Doc_Btn#xpath=//i[@class='ng-scope fa fa-paperclip']";
	public static String Order_PO_Doc_HeaderTxt ="PO_Doc_HeaderTxt#xpath=//*[@class='headtext ng-binding']";
	public static String Order_PO_Doc_UploadFile ="PO_Doc_UploadFileTxt#xpath=//label[contains(text(),'Upload File')]";
	public static String Order_PO_Doc_CloseBtn ="PO_Doc_CloseBtn#xpath=//*[text()='Close']";
	public static String Order_PO_Doc_UploadFiles ="PO_Doc_UploadFile#xpath=//*[@type='file']";

	public static String Order_PO_StatusDropdown_Cancelled ="Cancelled#xpath=//*[text()='Cancelled']";
	public static String Order_PO_StatusDropdown_On_Hold ="On_Hold#xpath=//*[text()='On-Hold']";
	public static String Order_PO_StatusDropdown_Changed ="Changed#xpath=//*[text()='Changed']";
	public static String Order_PO_StatusDropdown_Assigned ="Assigned#xpath=//*[text()='Assigned']";
	public static String Order_status_dropdown ="dropdown#xpath=//*[@id='status']";
	public static String Order_PO_StatusDropdown_Confirmed ="Confirmed#xpath=(//*[text()='Confirmed'])[1]";
	public static String Order_PO_SearchBtn ="SearchBtn#xpath=//button[text()='Search']";
	public static String Order_PO_first_dropdown ="PO_first_dropdown#xpath=(//*[@class='fa fa-chevron-down'])[1]";
	public static String Order_PO_first_printPo ="PO_first_PrintPo#xpath=(//*[text()='Print PO'])[1]";
	public static String Order_PO_first_PoLog ="PO_first_PoLog#xpath=(//*[text()='PO Log'])[1]";
	public static String Order_PO_PoLogHead ="PO_PoLogHead#xpath=(//*[@class='pagehead ng-binding'])[1]";
	public static String Order_PO_first_invoice ="PO_first_invoice#xpath=(//a[text()='Invoices'])[1]";
	public static String Order_PO_first_Addinvoice ="PO_first_Addinvoice#xpath=(//a[@ng-click='addInvoiceCall()'])[1]";
	public static String Order_PO_Invoice_Header ="Invoice_Header#xpath=//*[contains(text(),'Invoice for PO #:')]";
	public static String Order_PO_printPo_PDF ="PO_printPo_PDF#xpath=//*[@class='modal-header']//*[contains(text(),'Print PO')]";
	public static String Order_PO_first_AllInvoice ="PO_first_AllInvoice#xpath=(//*[text()='All Invoices'])[1]";
	public static String Order_PO_first_AllInvoice_Header ="PO_first_AllInvoice_Header#xpath=//*[@class='pagehead']";
	public static String Order_PO_Title ="PO_Title#xpath=//*[@ng-click=\"order('PONUM')\"]";
	public static String Order_PO_idList ="PO_Idlist#xpath=(//*[@ng-repeat='order in ordersData.purchaseOrders']/td/a)";

	public static String Assending ="Assending#xpath=//*[@class='fa fa-caret-up']";
	public static String Descending ="Descending#xpath=//*[@class='fa fa-caret-down']";
	public static String conditionForNoOrder ="conditionForNoOrder#xpath=//div[@class='panel-body']/tbody/tr/td";
	public static String Order_PO_StatusDropdown_PartialReceived ="PartialReceived#xpath=//*[text()='Partial Received']";
	public static String Order_PO_StatusDropdown_Completed ="Completed#xpath=//*[text()='Completed']";
	public static String Order_FacilityDropDown ="FacilityDropDown#xpath=//*[@id='facility']";



	//INVOICE 

	public static String Request_InvoicePageLink="InvoicePageLink#xpath=//*[@href='#/invoice']";
	public static String Invoice_InvoiceDesc="InvoiceDesc#xpath=//input[@id='txtInvNo']";
	public static String Invoice_wait="wait#xpath=//*[@id='ng-view']";
	public static String Invoice_InvoicePONum="Invoice_InvoicePONum#xpath=//div[@class='pagehead']/span";
	public static String Invoice_AddItemInInvoice="AddItemInInvoice#xpath=//select[@name='POItems']";
	public static String Invoice_AddItemButton="AddItemButton#xpath=//button[@ng-click='AddItemToInvoice()']";
	public static String Invoice_ItemQtyField="ItemQtyField#xpath=//input[id='ItemQty']";
	public static String Invoice_SaveButton="SaveButton#xpath=//button[@ng-click='saveInvoice()']";
	public static String Invoice_ConfirmButton="ConfirmButton#xpath=//button[@class='confirm']";
	public static String Invoice_DocPopUPText="DocPopUPText#xpath=//div[@class='modal-header']/div[@class='headtext ng-binding']";
	public static String Invoice_DocButton="DocButton#xpath=//button[@ng-click='attachFiles()']";
	public static String Invoice_UploadButton="UploadButton#xpath=//*[@id='pic']../label";
	public static String Invoice_UploadDocName="UploadDocName#xpath=(//*[@ng-repeat='item in rows']//A)[2]";
	//public static String Invoice_closeButton="closeButton#xpath=//button[text()='cancel()']";
	public static String Invoice_closeButton="closeButton#xpath=//*[@class='fa fa-2x fa-times']";
	public static String Invoice_SearchInInvoiceTextBox="SearchInInvoiceTextBox#xpath=//*[@id='search']";
	public static String Invoice_SearchButton="SearchButton#xpath=//button[@type='submit']";
	public static String Invoice_SelectPending="SelectPending#xpath=//div[@class='input-sm ui-select-container ui-select-bootstrap dropdown ng-not-empty ng-valid ng-touched ng-dirty ng-valid-parse']";
	public static String Invoice_SelectFirstInvoice="SelectFirstInvoice#xpath=//*[@class='table']/tbody/tr[1]/td[1]/a";
	public static String Invoice_SelectFirstPO="SelectFirstPO#xpath=//*[@class='table']/tbody/tr[1]/td[2]";
	public static String Invoice_SelectAllUserDropDown="SelectAllUserDropDown#xpath=//input[@type='search']";
	public static String Invoice_SelectAllUser="SelectAllUser#xpath=//*[text()='All']";
	public static String Invoice_PageHeadText="PageHeadText#xpath=//div[@class='pagehead']";
	public static String Invoice_PageHeadTextIncludingPONum="PageHeadTextIncludingPONum#xpath=//div[@class='pagehead']/span";
	public static String Invoice_clickOnUploadFIle="clickOnUploadFIle#xpath=//*[@type='file']";
	public static String Invoice_PONUmInHeader="clickOnUploadFIle#xpath=//*[@class='pagehead']/span";
	public static String Invoice_SelectFirstPONUmFromInvoicePage="SelectFirstPONUmFromInvoicePage#xpath=//*[@class='table']/tbody/tr[1]/td[2]";
	public static String Invoice_SelectFirstInvoiceFromInvoicePage="SelectFirstInvoiceFromInvoicePage#xpath=//*[@class='table']/tbody/tr[1]/td[1]/a";
	public static String Invoice_DownoadPDF="Invoice_DownoadPDF#xpath=(//*[@class='fa fa-download'])[1]";
	public static String Invoice_PrintPDF="PrintPDF#xpath=(//*[@ng-click='openPDF()'])[1]";
	public static String Invoice_PrintPReviewWindow="PrintPReviewWindow#xpath=//*[contains(text(),'Print Invoice')]";
	public static String Invoice_PrintPReviewclose="PrintPReviewclose#xpath=//*[@class='fa fa-times']";
	public static String Invoice_vendorDropDownLabels="vendorDropDownLabels#xpath=//*[text()='Vendor:']";
	public static String Invoice_vendorDropDowns="vendorDropDown#xpath=//*[@name='vendor_name']";
	public static String Invoice_statusDropDowns="statusDropDown#xpath=//*[@name='status']";
	public static String Invoice_statusDropDownLabel="statusDropDownLabel#xpath=//label[@for='ddlStatus']";
	public static String Invoice_SearchBYDueDateDropDowns="statusDropDown#xpath=//*[@name='dateRange']";
	public static String Invoice_SearchBYDueDateLabel="statusDropDownLabel#xpath=//*[text()='Search by Due-Date']";

	public static String Invoice_FacilityLabel="facilityLabel#xpath=//*[text()='Facility']";
	public static String Invoice_facilityDateDropDowns="facilityDateDropDowns#xpath=//*[@name='facility']";
	public static String Invoice_departmentLabel="departmentLabel#xpath=//label[@for='depts']";
	public static String Invoice_departmentDropDown="departmentDropDown#xpath=//*[@name='depts']";
	public static String Invoice_OrderedByLabel="OrderedByLabel#xpath=//*[text()='Ordered By:']";
	public static String Invoice_OrderedByDropDown="OrderedByDropDown#xpath=//*[@name='ddlOrderby']";
	public static String Invoice_SentToAccounting="SentToAccounting#xpath=//button[contains(text()='SentToAccounting')]";
	public static String Invoice_firstInvoice="first#xpath=//*[@class='table']//tr[1]/td[1]/a";
	public static String Invoice_Delete="Delete#xpath=(//i[@class='glyphicon glyphicon-trash text-danger action-button'])[1]";
	public static String Invoice_confirmButton="Invoice_confirmButton#xpath=//button[@class='confirm']";
	public static String Invoice_NoInvoiceFoundMessage="NoInvoiceFoundMessage#xpath=//*[text()='No Invoice Found']";


	//SHIPMENTS
	public static String Shipment_PageLink="PageLink#xpath=//*[@href='#/orders/shipments']";
	public static String Shipment_FirstShipmentNum="FirstShipmentNum#xpath=//*[@class='table table-striped']//tbody//tr[1]//td[1]/a[1]";
	public static String Shipment_ShipmentPONum="ShipmentPONum#xpath=//*[@class='table table-striped']//tbody/tr[1]/td[2]";
	public static String Shipment_TrackingNum="TrackingNum#xpath=//*[@class='table table-striped']//tbody/tr[1]//td/span";
	public static String Shipment_wait="wait#xpath=//*[@id='ng-view']";
	public static String Shipment_SearchTextBox="SearchTextBox#xpath=//*[@id='shipment-search']";
	public static String Shipment_SubmitButton="SubmitButton#xpath=//button[text()='Search']";
	//public static String Shipment_ShipmentDetailPAge="ShipmentDetailPage#xpath=//*[contains(text(),'Shipment#')]";
	public static String Shipment_ShipmentDetailPAge="ShipmentDetailPAge#xpath=//*[@class='pagehead ng-binding']";
	public static String Shipment_StatusDropDown="StatusDropDown#xpath=//*[@id='sel1']";

	public static String Shipment_ReceiveButtonText="ReceiveButtonText#xpath=//div[@class='pull-right']/button[3]";
	public static String Shipment_CloseButton="CloseButton#xpath=//div[@class='pull-right']/button[4]";

	//ORGSettings page

	public static String orgsetting_crosswalk="crosswalk#xpath=//*[@id='use_cross_walk_as_item_identifier']";
	public static String orgsetting_crosswalktext="crosswalktext#xpath=//input[@id='cross_walk_desc']";
	public static String orgsetting_IntactSetting="IntactSetting#xpath=//input[@id='intacct_integration']";
	public static String orgsetting_QuickBookIntegrationSetting="QuickBookIntegrationSetting#xpath=//input[@id='quick_book_integration']";		
	public static String orgsetting_AdminMenuText="AdminMenuText#xpath=//admin-menu//li[text()='Admin']";			
	public static String orgsetting_orgPageTextt="orgPageTextt#xpath=//div[@class='pagehead']";		
	public static String orgsetting_orgFeaturesPageText="orgFeaturesPage#xpath=//*[@class='sub-header-block' and text()='Inventory']";
	public static String orgsetting_orgBudgetPage="orgBudgetPage#xpath=//a[@href='#/admin/organization-budget']";
	public static String orgsetting_orgBudgetPageText="orgBudgetPageText#xpath=//div[@class='pagehead']";

	//Users Page
	public static String Users_page="Users_page#xpath=//*[@href='#/admin/users']";
	public static String Users_SearchByText="SearchByText#xpath=//*[@for='search-search']";
	public static String Users_SearchTextBox="SearchTextBox#xpath=//*[@class='form-group']//*[@id='search-search']";
	public static String Users_FacilityText="FacilityText#xpath=//*[@for='search-facility']";
	public static String Users_FacilityDropDown="FacilityDropDown#xpath=//*[@id='search-facility']";
	public static String Users_StatusText="Status#xpath=//*[@for='search-status']";
	public static String Users_StatusDropDown="StatusDropDown#xpath=//*[@id='search-status']";
	public static String Users_SearchButton="SearchButton#xpath=//button[@type='submit']";
	public static String Users_FirstUserName="FirstUserName#xpath=//*[@id='isotopeContainer']//*[@class='user-detail-container']//*[@class='ng-binding'][1]";
	public static String Users_FirstUserEmail="FirstUserEmail#xpath=//*[@id='isotopeContainer']//*[@class='user-detail-container']//*[@class='ng-binding'][3]";
	public static String Users_EditFirstUser="EditFirstUser#xpath=(//*[@ng-click='editUser(adminUserData.id)'])[1]";
	public static String Users_EditUserTextPopUp="EditUserTextPopUp#xpath=//*[contains,(text(), 'Edit User')]";
	public static String Users_NameEditBox="NameEditBox#xpath=//*[@id='name']";
	public static String Users_EmployeeEditBox="NameEditBox#xpath=//*[@id='employee_no']";
	public static String Users_NickNameEditBox="NickNameEditBox#xpath=//*[@id='nickname']";
	public static String Users_EmailEditBox="EmailEditBox#xpath=//*[@id='email']";
	public static String Users_PasswordEditBox="PasswordEditBox#xpath=//*[@id='password']";
	public static String Users_ConfirmPasswordEditBox="ConfirmPasswordEditBox#xpath=//*[@id='con_password']";
	public static String Users_ClosePopup="ClosePopup#xpath=//i[@class='fa fa-2x fa-times']";
	public static String Users_AddNewUser="AddNewUser#xpath=//button[@ng-click='createUser()']";
	public static String Users_AddUserPopupText="AddUserPopupText#xpath=//*[@class='modal-content']//span[2]";
	public static String Users_AddUserPopupClose="AddUserPopupClose#xpath=//*[@class='fa fa-2x fa-times']";
	public static String Users_EditUserButton="EditUserButton#xpath=//button[@ng-click='editUser(adminUserData.id)']";
	//public static String Users_EditUserButton="EditUserButton#xpath=//button[text()='Edit']";
	public static String Users_EditUserPopupText="EditUserPopupText#xpath=//*[@class='modal-content']//span[1]";
	public static String Users_ChangePasswordButton="ChangePasswordButton#xpath=(//button[@ng-click='changePassword(adminUserData.id)'])[1]";
	public static String Users_ChangePasswordPopUpText="ChangePasswordPopUpText#xpath=//*[@class='headtext']";
	public static String Users_SaveButton="SaveButton#xpath=//*[@class='modal-footer']//button[@ng-click='saveUserFn(user)']";
	public static String Users_FacilityDropDownAddUSer="FacilityDropDownAddUSer#xpath=//*[@id='facility']";
	public static String Users_DepartmentDropDownAddUSer="FacilityDropDownAddUSer#xpath=//*[@id='department']";
	public static String Users_WaitDepartmentDropDownAddUSer="WaitFacilityDropDownAddUSer#xpath=//*[@id='department']/option";
	public static String Users_InventoryDropDownAddUSer="InventoryDropDownAddUSer#xpath=//*[@id='inventory']";
	public static String Users_WaitInventoryDropDownAddUSer="WaitInventoryDropDownAddUSer#xpath=//*[@id='inventory']";
	public static String Users_vendorAccessTab="vendorAccessTab#xpath=//li[@header='Vendor Access']/a";
	public static String Users_SelectVendorsDropDown="SelectVendorsDropDown#xpath=//*[@for='itemslist']//i[@class='fa fa-ellipsis-v autocomplete-show'";
	public static String Users_SelectAllVendors="SelectAllVendors#xpath=//button[contains(text(), 'Select all') and contains(@ng-click='selectAllItems()')]";
	public static String Users_waitforResult="waitforResult#xpath=//*[@class='grid-item ng-scope']";
	public static String Users_facilityTab="facilityTab#xpath=//*[text()='Facility Access']";

	public static String Users_searchTextBoxFacTab="searchTextBoxFacTab#xpath=//*[@id='searchFacility']";
	public static String Users_AllPermissionsCheckBox="AllPermissionsCheckBox#xpath=//*[@id='facility_checkbox_5']";
	public static String Users_saveButton="saveButton#xpath=//*[@ng-click='saveUserFn(user)']";
	//Facilities

	public static String Facilty_FacilityLink="FacilityLink#xpath=//*[@href='#/admin/facility']";	
	public static String Facilty_WaitforTableElem="WaitforTableElem#xpath=//*[@id='reqListing']";
	public static String Facilty_firstFacility="firstFacility#xpath=//*[@id='reqListing']//td[@class='ng-binding'][1]";
	public static String Facilty_SearchBox="SearchBox#xpath=//*[@id='search-search']";
	public static String Facilty_SearchButton="SearchButton#xpath=//button[@type='submit']";
	public static String Facilty_AddFacilityButton="AddFacilityButton#xpath=//button[@ng-click='createFacility()']";
	public static String Facilty_AddFacilityText="AddFacilityText#xpath=//*[@class='modal-header']//span[2]";
	public static String Facilty_EditFacility="EditFacility#xpath=//i[@class='fa fa-edit']";
	public static String Facilty_EditFacilityText="AddFacilityText#xpath=//*[@class='modal-header']//span[1]";
	public static String Facilty_DepartmentNames="DepartmentNames#xpath=//ul[@class='facility_department_sublist'][1]//li";
	public static String Facilty_firstdrillDown="firstdrillDown#xpath=(//button[@class='btn btn-sm btn-default rounded-button ng-scope'])[1]";
	public static String Facility_wait="wait#xpath=//*[@id='reqListing']";

	//Department

	public static String Dep_PageLink="PageLink#xpath=//*[@href='#/admin/department']";
	public static String Dep_SelectFac="Dep_SelectFac#xpath=//*[@name='facility']";
	public static String Dep_SearchButton="SearchButton#xpath=//button[@type='submit']";
	public static String Dep_SearchTextBox="SearchTextBox#xpath=//*[@id='search-search']";
	public static String Dep_allDeps="allDeps#xpath=//*[@class='grid-item ng-scope']";
	public static String Dep_firstDeps="firstDeps#xpath=(//*[@class='grid-item ng-scope']//*[@class='grid-heading grid-title-label ng-binding'])[1]";
	public static String Dep_wait="wait#xpath=//*[@id='isotopeContainer-']";
	public static String Dep_addNewDep="addNewDep#xpath=//*[@class='pagehead']//button[text()='Add']";
	public static String Dep_addFacName="addFacName#xpath=//*[@id='facility']";
	public static String Dep_addFacmnemonic="addmnemonic#xpath=//*[@id='mnemonic']";
	public static String Dep_addName="addDepName#xpath=//*[@id='name']";
	public static String Dep_saveButton="saveButton#xpath=//*[@class='modal-footer']//button[text()='Save']";
	public static String Dep_editButton="editButton#xpath=//*[@class='grid-row-value']//button[text()='Edit']";
	public static String Dep_DeleteButton="DeleteButton#xpath=//*[@class='grid-row-value']//button[text()='Delete']";
	public static String Dep_confirmButton="confirmButton#xpath=//*[@class='confirm']";

	//Physician
	public static String Phy_pageLink="pageLink#xpath=//*[@href='#/preference-card/physicians']";
	public static String Phy_SelectFac="Phy_SelectFac#xpath=//*[@id='facility']";
	public static String Phy_SearchButton="SearchButton#xpath=//button[@type='submit']";
	public static String Phy_waitfortable="waitforTable#xpath=//*[@class='table table-striped']";
	public static String Phy_addNewPhy="addNewPhy#xpath=//*[@class='pagehead']//button[text()='Add Physician']";
	public static String Phy_addfirstName="Phy_addfirstName#xpath=//*[@id='first_name']";
	public static String Phy_addlastName="Phy_addlastName#xpath=//*[@id='last_name']";
	public static String Phy_addnpi="Phy_addnpi#xpath=//*[@id='mrn_number']";
	public static String Phy_facDropDown="Phy_facDropDown#xpath=//*[@for='itemslist']";
	public static String Phy_typeDefault_Fac="Phy_typeDefault_Fac#xpath=//*[@id='itemslist']";
	public static String Phy_selectDef_Fac="Py_selectDef_Fac#xpath=//*[@id='itemslist']//li[1]//*[@class='vendor-lit-container autocomplete-show']";
	public static String Phy_plusIcon="plusIcon#xpath=(//*[@ng-click='selectItem(item.name,item.id)'])[1]";
	public static String Phy_saveButton="saveButton#xpath=//*[@class='modal-footer']//button[text()='Save']";
	public static String Phy_SearchTextBox="SearchTextBox#xpath=//*[@id='search']";
	public static String Phy_firstFacInDropDown="firstFacInDropDown#xpath=//ul[@class='vendor-list autocomplete-show']//li[1]";
	public static String Phy_firstFacAfterSearch="firstFacAfterSearch#xpath=//*[@class='table table-striped']//tr/td[1]";
	public static String Phy_firstNPIAfterSearch="firstNPIAfterSearch#xpath=//*[@class='table table-striped']//tr/td[2]";
	public static String Phy_wait="wait#xpath=//*[@id='ng-view']";
	public static String Phy_wait_onEdit="onEdit#xpath=(//*[@class='fa fa-edit'])[1]";
	public static String Phy_editButton="editButton#xpath=(//*[@class='fa fa-edit'])[1]";
	public static String Phy_deleteButton="deleteButton#xpath=(//*[@class='fa fa-trash-o'])[1]";
	public static String Phy_confirmButton="confirmButton#xpath=//*[@class='confirm']";
	//Receive Module	
	public static String Receive_statusDropdownOptions="statusDropdownOptions#xpath=//*[@id='status']/ul/li//span[@class='ui-select-choices-row-inner']/span";
	public static String Receive_pageLink="pageLink#xpath=//*[@href='#/orders/receive']";
	public static String Receive_wait="wait#xpath=//*[@class='table']/thead/tr";
	public static String Receive_statusDropdown="statusDropdown#xpath=//*[@id='status']";
	public static String Receive_vendorDropdown="vendorDropdown#xpath=//*[@id='vendor']";
	public static String Receive_typeDropdown="typeDropdown#xpath=//*[@id='type']";
	public static String Receive_facilityDropdown="typeDropdown#xpath=//*[@id='facility']";

	public static String Receive_UsersDropdown="UsersDropdown#xpath=//*[@id='creater']";


	public static String Receive_departmentDropdown="typeDropdown#xpath=//*[@id='depts']";
	public static String Receive_createdByDropdown="typeDropdown#xpath=//*[@id='creater']";
	public static String Receive_NotesLinkWait="NotesLinkWait#xpath=//*[@class='table']";
	public static String Receive_DocLinkWait="NotesLinkWait#xpath=//*[@class='table']";
	public static String Receive_NotesLink="NotesLink#xpath=(//a[@ng-click='openPONote(order)'])[1]";

	public static String Receive_NotesLinkPODetail="NotesLinkPODetail#xpath=//i[@class='ng-scope fa fa-sticky-note-o']";
	public static String Receive_NotesLinkText="NotesLinkText#xpath=(//*[@class='modal-header']//span[@class='ng-binding'])[1]";
	public static String Receive_AddNewNotesLink="AddNewNotesLink#xpath=//button[@ng-click='createNewNoteFn();']";
	public static String Receive_DocsLink="DocsLink#xpath=(//*[@class='pull-right dropdown']/a[@ng-click='attachFiles(order)'])[1]";
	public static String Receive_DocsLinkPODetail="DocsLinkPODetail#xpath=(//*[@class='ng-scope fa fa-paperclip'])[1]";
	public static String Receive_DocsLinkText="DocsLinkText#xpath=(//*[@class='headtext ng-binding'])[1]";
	public static String Receive_DrillDownIcon="DrillDownIcon#xpath=(//*[@ng-click='getInvoiceData(order)'])[1]";
	public static String Receive_DrillDownIconwait="DrillDownIconwait#xpath=//*[@class='table']";
	public static String Receive_DrillDownPODetailPage="DrillDownPODetailPage#xpath=//button[@type='button' and @ng-click='getInvoiceData(orderData.order);']";
	public static String Receive_PrintPOLink="PrintPOLink#xpath=(//*[@class='dropdown-menu']//*[@ng-click='openPDF()'])[1]";
	public static String Receive_PrintPOLinkPoDetail="PrintPOLinkPoDetail#xpath=//*[@ng-click='openPDF()']";
	public static String Receive_PrintPOText="PrintPOText#xpath=(//*[@class='modal-header']//*[@class='ng-binding'])[1]";
	public static String Receive_PrintPReviewclose="PrintPReviewclose#xpath=//*[@class='fa fa-times']";
	public static String Receive_PrintCloseclose="PrintPReviewclose#xpath=//*[@class='fa fa-2x fa-times']";
	public static String Receive_InvoiceForText="InvoiceForText#xpath=(//*[@class='pagehead'])[1]";
	public static String Receive_PrintPODetailPage="PrintPODetailPage#xpath=[@class='modal-header']/span";
	public static String Receive_AllInvoiceLink="AllInvoiceLink#xpath=(//a[text()='All Invoices'])[1]";
	public static String Receive_firstPONum="firstPONum#xpath=(//*[@class='table']//a[@class='ng-binding'])[1]";
	public static String Receive_PrintItems="PrintItems#xpath=(//*[@ng-click='downloadPdf(order)']";
	public static String Receive_PrintitemsText="PrintitemsText#xpath=(*//[@class='headtext']/span[1])";
	public static String Receive_firstPOHeadingText="firstPOHeadingText#xpath=//*[@class='col-sm-18']/*[@class='pagehead ng-binding' and contains(text(),'Purchase Order #')]";
	public static String Receive_itemNames="itemNames#xpath=//table[@class='table table-body-striped ng-scope']//item-info[@class='ng-isolate-scope']";
	public static String Receive_PODetailDrillDown="PODetailDrillDown#xpath=//button[@type='button' and @ng-click='getInvoiceData(orderData.order);']";
	public static String Receive_AdditemsToCart="AdditemsToCart#xpath=//li[@ng-if='allowAddToCart(order)']/a[@ng-click='AddOrderItemsTOCart(order);']";
	public static String Receive_searchButton="searchButton#xpath=//button[@type='submit']";
	public static String Receive_itemsNamesInCart="itemsNamesInCart#xpath=(//*[@value='name'])[1]";

	public static String Receive_AddNewNote="AddNewNote#xpath=//*[@class='form-group']/textarea";
	public static String Receive_AddNewNoteButton="AddNewNoteButton#xpath=//button[@ng-click='add()']";
	public static String Receive_searchBox="searchBox#xpath=//input[@type='text']";
	public static String Receive_searchedfirstNote="searchedfirstNote#xpath=(//*[@class='sorting_1 poNote ng-binding'])[1]";
	public static String Receive_DeleteIcon="DeleteIcon#xpath=(//i[@class='fa fa-trash'])[1]";

	//public static String Receive_NotesDeleteIcon="NotesDeleteIcon#xpath=(//*[@ng-click='deleteNoteFn(note.id, $index)']/i)[1]";
	public static String Receive_NotesDeleteIcon="NotesDeleteIcon#xpath=(//i[@class='fa fa-trash-o'])[1]";
	public static String Receive_confirmButton="confirmButton#xpath=//button[@class='confirm']";
	//public static String Receive_NoNOteavailable="NoNOteavailable#xpath=//*[@class='row']//h4[1]";
	public static String Receive_NoNOteavailable="NoNOteavailable#xpath=//h4[text()='No note available.']";



	public static String Receive_POLOg="POLOg#xpath=//*[@ng-click='openPOLog(order)']";
	public static String Receive_POLOgText="POLOgText#xpath=//*[@class='pagehead ng-binding']";
	public static String Receive_printPO="printPO#xpath=//a[@ng-click='downloadPdf(order);']";	
	public static String Receive_printItemsDetailPage="printItemsDetailPage#xpath=//a[text()='Print Items']";
	public static String Receive_printPOText="printPOText#xpath=//*[@class='headtext']/span";
	public static String Receive_uploadFile="uploadFile#xpath=//*[@type='file']";
	public static String Receive_PODetailAddInvoice ="PODetailAddInvoice#xpath=//i[@class='fa fa-plus']";
	public static String Receive_PODetailInvoiceText ="PODetailInvoiceText#xpath=//*[@class='pagehead']";
	public static String Receive_AllInvoiceDetailPage ="AllInvoiceDetailPage#xpath=//*[text()='All Invoices']";
	public static String Receive_POLogDetailPage ="POLogDetailPage#xpath=//*[text()='PO Log']";
	public static String Receive_AddOrderItemsTOCartDetaipPage="AddOrderItemsTOCartDetaipPage#xpath=//a[@ng-click='AddOrderItemsTOCart();']";
	public static String Receive_SearchTextBox="SearchTextBox#xpath=//*[@id='PONUM']";
	public static String Receive_POApprovalLogs="POApprovalLogs#xpath=//*[text()='PO Approval Log']";
	public static String Receive_POApprovalLogsPopUpText="POApprovalLogsPopUpText#xpath=//*[contains(text(),' PO Approval Logs for PO #')]";
	public static String Receive_PODetailNotesLinkDrillDown="PODetailNotesLinkDrillDown#xpath=//*[text()='Notes']";
	public static String Receive_PODocsDrillDown="POApprovalLogs#xpath=//*[text()='Documents']";	 
	public static String Receive_UploadDocNameDetailPage="UploadDocName#xpath=(//a[@class='ng-binding'])[2]";
	public static String Receive_waitDoc="waitDoc#xpath=ng-click=//*[@ng-repeat='item in rows'])";
	public static String Receive_ReceiveOnlyText="ReceiveOnlyText#xpath=//*[text()='Mark as Receive Only']";
	public static String Receive_ShowTour="ShowTour#xpath=//*[text()='Show Tour']";
	public static String Receive_NextButton="NextButton#xpath=//button[@class='btn btn-sm btn-default ng-scope' and text='Next �']";
	public static String Receive_EndButton="EndButton#xpath=//button[@class='btn btn-sm btn-primary' and text='End tour']";
	public static String Receive_patialButtonText="patialButtonText#xpath=(//button[@id='btnAdd'])[4]//span";
	public static String Receive_completeButtonText="completeButton#xpath=(//button[@id='btnAdd'])[5]//span";
	public static String Receive_CloseButtonText="closeButton#xpath=(//button[@id='btnAdd'])[10]";
	public static String Receive_Depatment="Depatment#xpath=(//*[@ng-if='!orderData.is_po_editable || !canModify']/strong)[1]";
	public static String Receive_Physician="Physician#xpath=(//*[@ng-if='!orderData.is_po_editable || !canModify']/strong)[2]";
	public static String Receive_OrdersListingPageText="OrdersListingPageText#xpath=//*[@class='pagehead ng-binding']";
	public static String Receive_ShowTourText="ShowTourText#xpath=//*[@class='popover-inner tour-step-inner']/h3";
	public static String Receive_selectedFacInDropDown="selectedFacInDropDown#xpath=//*[@id='facility']//span[1]";

	public static String Receive_selectedUserInDropDown="selectedUserInDropDown#xpath=//*[@id='creater']//span[@class='ui-select-match-text pull-left']/span";


	//OPERATING ROOM

	public static String OP_ORoomPageLink="CreateORoomPageLink#xpath=//a[@href='#/preference-card/operating-rooms']";
	public static String OP_wait="wait#xpath=//*[@class='table table-striped']/thead/tr";
	public static String OP_wait2="wait#xpath=//*[@id='ng-view']";
	
	public static String OP_CreatenewOP="CreatenewOP#xpath=//button[@class='btn btn-primary pull-right']";
	public static String OP_OpName="OpName#xpath=//*[@id='name']";
	public static String OP_CreatenewOPText="CreatenewOPText#xpath=//*[@class='headtext']/span";
	public static String OP_FacilityDropDown="FacilityDropDown#xpath=//*[@id='facility']";
	public static String OP_disabledSaveButton="disabledSaveButton#xpath=//*[@class='modal-footer']/button[1]";
	public static String OP_enabledSaveButton="enabledSaveButton#xpath=//*[@class='modal-footer']/button[@disabled='disabled']";
	public static String OP_searchTextBox="searchTextBox#xpath=//input[@id='search']";
	public static String OP_searchButton="searchButton#xpath=//button[@type='submit']";
	public static String OP_firstOPRoom="firstOPRoom#xpath=//*[@class='table table-striped']//tr/td";
	public static String OP_EditButton="EditButton#xpath=//*[@class='pull-right']//i[1]";
	public static String OP_DeleteButton="DeleteButton#xpath=//*[@class='pull-right']//i[@class='fa fa-trash-o']";
	public static String OP_confirmButton="confirmButton#xpath=//button[@class='confirm']";


	//Patients

	public static String Patient_PatientsPageLink="PatientsPageLink#xpath=//a[@href='#/preference-card/patients']"; 
	public static String Patient_wait="wait#xpath=//*[@id='ng-view']";
	public static String Patient_getfacilityName = "getfacilityName#xpath=//*[@ng-click='selectItem();']";
	public static String Patient_ShopfaclitySelect = "ShopfaclitySelect#xpath=//*[@title='Select Facility']";
	public static String Patient_Shopfaclity = "Shopfaclity#xpath=//*[text()='Select Facility']";
	public static String Patient_plusIcon = "plusIcon#xpath=(//button[@class='btn btn-sm btn-default ng-scope'])[1]";
	public static String Patient_firstPatient="firstPatient#xpath=//*[@class='table table-striped']//tbody/tr[1]/td[1]";
	//public static String Patient_firstPatient="firstPatient#xpath=(//tbody//*[@ng-repeat-start='patient in patients'])[1]";
	public static String Patient_firstPatientwait="firstPatient#xpath=//*[@class='table table-striped']//tbody";
	public static String Patient_EditPAtient="EditPAtient#xpath=(//*[@class='fa fa-edit'])[1]";
	public static String Patient_PrintIcon="PrintIcon#xpath=(//*[@class='fa fa-print'])[1]";
	public static String Patient_EditPAtientPopUp="EditPAtientPopUp#xpath=//*[@class='headtext']/span";
	public static String Patient_EditPAtientPopUpCancel="EditPAtientPopUpCancels#xpath=//*[@class='fa fa-2x fa-times']";
	public static String Patient_ClosePreview="ClosePreview#xpath=//*[@class='fa fa-times]";
	public static String Patient_previewpopup="previewpopup#xpath=//*[@class='modal-header']";
	public static String Patient_AddPatient="AddPatient#xpath=//button[@class='btn btn-primary pull-right ng-scope']";
	public static String Patient_SaveButton="SaveButton#xpath=//*[@class='modal-footer']";
	public static String Patient_firstName="firstName#xpath=//*[@id='first_name']";
	public static String Patient_middleName="middleName#xpath=//*[@id='middle_name']";
	public static String Patient_lastName="lastName#xpath=//*[@id='last_name']";
	public static String Patient_mrnNumber="mrnNumber#xpath=//*[@id='mrn_number']";
	public static String Patient_accNumber="accNumber#xpath=//*[@id='account_number']";
	public static String Patient_dob="dob#xpath=//*[@id='dob']";
	public static String Patient_facDropDown="facDropDown#xpath=//*[@id='facility']";
	public static String Patient_firstFac="firstFac#xpath=//li[@class='ui-select-choices-group']//span[@class='ng-binding ng-scope'][1]";
	public static String Patient_disabledSaveButton="disabledSaveButton#xpath=//*[@class='modal-footer']/button[@class='btn btn-primary']";
	public static String Patient_enabledSaveButton="enabledSaveButton#xpath=//*[@class='modal-footer']/button[@disabled='disabled']";
	public static String Patient_searchTextBox="searchTextBox#xpath=//*[@id='search']";
	public static String Patient_searchbutton="searchbutton#xpath=//button[@type='submit']";
	public static String Patient_add01="add01#xpath=//*[@id='address1']"; 
	public static String Patient_add02="add02#xpath=//*[@id='address2']";
	public static String Patient_city="city#xpath=//*[@id='city']";
	public static String Patient_state="state#xpath=//*[@id='state']";
	public static String Patient_zip="zip#xpath=//*[@id='zip']";
	public static String Patient_Phone="Phone#xpath=//*[@id='primary_phone']";
	public static String Patient_secphone="secphone#xpath=//*[@id='secondary_phone']"; 
	public static String Patient_DOS="DOS#xpath=//*[@id='date_of_service']"; 
	public static String Patient_Addprefcard="Addprefcard#xpath=//i[@class='fa fa-paperclip']"; 	 
	public static String Patient_AddprefcardPopUpText="AddprefcardPopUpText#xpath=//*[@class='modal-header']";
	public static String Patient_DeleteIcon="DeleteIcon#xpath=//i[@class='fa fa-trash-o']";



	//Cases
	public static String Cases_CasesPageLink="CasesPageLink#xpath=//a[@href='#/preference-card/cases']"; 
	public static String Cases_SelectDateDropDown="SelectDateDropDown#xpath=//*[@id='dateRange']"; 
	public static String Cases_SearchButton="SearchButton#xpath=//button[@type='submit']";
	public static String Case_firstPatient="firstPatient#xpath=(//*[@class='table table-striped']//tbody/tr/td[2]/nobr)[1]";
	public static String Case_firstCase="firstCase#xpath=//*[@class='table table-bordered']/tr//td[2]";
	// public static String Case_firstCase="firstCase#xpath=(//*[@class='table table-bordered'])[1]";
	public static String Cases_searchTextBox="searchTextBox#xpath=//*[@id='search']"; 
	public static String Cases_Editbutton="Editbutton#xpath=(//i[@class='fa fa-edit'])[1]";
	public static String Cases_EditCase="EditCase#xpath=//*[@class='pagehead ng-binding']";
	public static String Cases_Printbutton="Printbutton#xpath=(//i[@class='fa fa-print'])[1]";
	public static String Cases_PreviewPopUp="PreviewPopUp#xpath=//*[@class='modal-header']";

	//Procedures

	public static String Procedure_PageLink="PageLink#xpath=//*[@href='#/preference-card/procedures']";
	public static String Procedure_firstElem="firstElem#xpath=//table[@class='table table-striped']//tbody/tr[1]/td[1]";
	public static String Procedure_AddProcedure="AddProcedure#xpath=//a[@href='#/preference-card/procedure/create']";
	public static String Procedure_addName="addName#xpath=//input[@id='name']";
	public static String Procedure_SelectPhysican1="SelectPhysican1#xpath=(//*[@ng-click='setSelectedPhysicians()'])[1]";

	public static String Procedure_SelectPhysican1Label="SelectPhysican1Label#xpath=(//*[@class='checkbox ng-scope'])[1]";
	public static String Procedure_SelectPhysican1Labe2="SelectPhysican2Label#xpath=(//*[@class='checkbox ng-scope'])[2]";

	public static String Procedure_SelectPhysican2="SelectPhysican2#xpath=(//*[@ng-click='setSelectedPhysicians()'])[2]";
	public static String Procedure_cptCode="cptCode#xpath=//input[@id='cpt_code']";	 
	public static String Procedure_SaveButton="SaveButton#xpath=//button[@class='btn btn-primary pull-right ng-scope' and @ng-click='saveProcedureFn(procedure)']";
	public static String Procedure_SearchTextBox="SearchTextBox#xpath=//*[@id='search']";
	public static String Procedure_wait="wait#xpath=//*[@id='ng-view']";
	public static String Procedure_EditButton="EditButton#xpath=(//i[@class='fa fa-edit'])[1]";
	public static String Procedure_plusIcon="plusIcon#xpath=(//button[@class='btn btn-sm btn-default ng-scope'])[1]";
	public static String Procedure_DeleteButton="DeleteButton#xpath=(//i[@class='fa fa-trash-o'])[1]";
	public static String Procedure_waitForDelete="waitForDelete#xpath=//*[@class='pull-right']";
	public static String Procedure_ConfirmButton="ConfirmButton#xpath=//*[@class='confirm']";
	public static String Procedure_waitForConfirmButton="waitForConfirmButton#xpath=//*[@class='sa-confirm-button-container']";
	public static String Procedure_NoProcText="NoProcText#xpath=//table[@class='table table-striped']/thead/tr[2]/td";
	public static String Procedure_firstPhysician="firstPhysician#xpath=//table[@class='table table-bordered']//tbody/tr[1]/td";

	//PLANNER
	public static String Planner_PageLink="PageLink#xpath=//*[@href='#/preference-card/planner']";
	public static String Planner_datatime630="datatime630#xpath=//tr[@data-time='06:30:00' and @class='fc-minor']";
	public static String Planner_createCaseHeading="createCaseHeading#xpath=(//*[@class='modal-header']//h4)";
	public static String Planner_createPatient="createPatient#xpath=//button[@ng-click='addPatientFn();']";
	public static String Planner_popUpText="popUpText#xpath=//*[@class='headtext']/span";
	public static String Planner_AddPatient="AddPatient#xpath=//*[@class='modal-footer']/button[@class='btn btn-primary pull-right ng-scope']";


	//User;s default FACILITy

	public static String Profile_defaultFacility="defaultFacility#xpath=(//*[@class='form-horizontal custom-form'])[2]//*[@class='form-group']//span";


	//PrefCard

	public static String prefCard_PageLink="PageLink#xpath=//*[@href='#/preference-card/cards']";
	public static String prefCard_wait="wait#xpath=//*[@id='ng-view']";
	public static String prefCard_AddPrefCard="AddPrefCard#xpath=//*[@href='#/preference-card/card/create']";
	// public static String prefCard_AddPrefCard="AddPrefCard#xpath=Patient_firstPatient
	public static String prefCard_CardName="CardName#xpath=//*[@id='cardName']"; 
	public static String prefCard_PhysicianName="PhysicianName#xpath=//*[@id='physician']"; 
	public static String prefCard_GlovesSize="GlovesSize#xpath=//*[@id='gloves_size']"; 
	public static String prefCard_addStage="addStage#xpath=//input[@value='Add Stage']";	 
	public static String prefCard_addStageText="addStageText#xpath=//*[@class='modal-header']/h4";
	public static String prefCard_CanelBUtton="CanelBUtton#xpath=//*[@class='modal-footer']//button[text()='Cancel']";
	public static String prefCard_addStageName="addStageName#xpath=//*[@id='prefCardStage']";
	public static String prefCard_addStageButton="addStageButton#xpath=//button[@class='btn btn-primary ng-binding']";
	public static String prefCard_reorderStageButton="reorderStageButton#xpath=//*[@value='Re-order Stage']";
	public static String prefCard_firstStage="firstStage#xpath=//ul[@dnd-list='Ctrl.prefCardStages']/li[1]//p";
	public static String prefCard_secondStage="secondStage#xpath=//ul[@dnd-list='Ctrl.prefCardStages']/li[2]//p";
	public static String prefCard_DragfirstStage="DragfirstStage#xpath=//ul[@dnd-list='Ctrl.prefCardStages']/li[1]";
	public static String prefCard_DragsecondStage="DragsecondStage#xpath=//ul[@dnd-list='Ctrl.prefCardStages']/li[2]";
	public static String prefCard_addItemInCart = "addItemInCart#xpath=//button[@id='btnAdd']";
	public static String prefCard_searchInCart = "searchInCart#xpath=//*[@id='searchInput']";
	public static String prefCard_catName = "catName#xpath=(//tr//h4)[1]";	
	public static String prefCard_InitialopenQty = "InitialopenQty#xpath=//*[@editable-number='prefCardStageItem.open']//span";
	public static String prefCard_IntialHoldQty = "intialHoldQty#xpath=//*[@editable-number='prefCardStageItem.hold']//span";
	public static String prefCard_plusIconOpenQTY = "plusIconOpenQTY #xpath=//*[@editable-number='prefCardStageItem.open']//button[2]";
	public static String prefCard_plusIconHoldQTY= "plusIconHoldQTY#xpath=//*[@editable-number='prefCardStageItem.open']//button[2]";
	public static String prefCard_deleteQty= "deleteQty#xpath=//i[@class='glyphicon glyphicon-trash']";
	public static String prefCard_confirmButton= "deleteQty#xpath=//button[@class='confirm']";
	public static String prefCard_NoItemText= "NoItemText#xpath=//h4[@class='text-center']";
	public static String prefCard_removeStage="removeStage#xpath=//*[@ng-click='removeStage(prefCardStage)']";
	public static String prefCard_copyStages="copyStages#xpath=//input[@value='Copy Stages']";
	public static String prefCard_copyStagesPopUpText="copyStagesPopUpText#xpath=//*[@class='modal-header']//h4";
	public static String prefCard_copyPredCardDrpDown="copyPredCardDrpDown#xpath=//*[@id='prefCard']";
	public static String prefCard_copyPredCardStageDroppDown="copyPredCardStageDroppDown#xpath=//*[@id='prefCardStage']";
	public static String prefCard_addButtton="addButtton#xpath=//button[text()='ADD']";
	public static String prefCard_copyButtton="addButtton#xpath=//button[text()='Copy']";
	public static String prefCard_stageName="stageName#xpath=(//uib-tab-heading)[1]";
	public static String prefCard_saveButton="saveButton#xpath=//*[@ng-click='savePreferenceCard(preferenceCard)']";
	public static String prefCard_searchTextBox = "searchTextBox#xpath=//*[@id='search']";
	public static String prefCard_firstprefcard = "firstprefcard#xpath=//*[@class='table table-striped']//tr[1]/td[1]";
	public static String prefCard_removePrefCard = "removePrefCard#xpath=//i[@class='fa fa-trash-o']";
	public static String prefCard_NoPrefCardText = "NoPrefCardText#xpath=//td[@class='text-center']";
	public static String prefCard_copyCard= "copyCard#xpath=//i[@class='fa fa-file-o']";
	public static String prefCard_copyCardPopUpText= "copyCardPopUpText#xpath=//*[@class='modal-header']//h4";

	//Manage Inventory
	public static String manageInv_pageLink= "pageLink#xpath=//*[@href='#/inventory']";
	public static String manageInv_firstItemDesc= "firstItemDesc#xpath=(//*[@class='item-description']/span)[1]";
	public static String manageInv_wait2= "wait#xpath=//*[@id='ng-view']";
	public static String manageInv_searchTextBox= "searchTextBox#xpath=//*[@id='PONUM']";
	public static String manageInv_searchButton= "searchButton#xpath=//*[text()='Search']";
	public static String manageInv_firstItemSku= "firstItemSku#xpath=//*[@class='vendor_info ng-binding'][1]//span[2]";
	public static String manageInv_EditIem= "EditIem#xpath=//*[@class='glyphicon glyphicon-pencil text-success action-button']";
	public static String manageInv_actaulFac= "actaulFac#xpath=//*[@class='pagehead']//a";
	public static String manageInv_wait= "wait#xpath=//*[@id='itemmasterListing']";
	public static String manageInv_editItem= "editItem#xpath=(//*[@class='glyphicon glyphicon-pencil text-success action-button'])[1]";
	public static String manageInv_InventoryTab = "InventoryTab#lnktext=Inventories";
	public static String manageInv_Inv_checkBoxes= "Inv_checkBoxes=#xpath=//*[contains(@id,'tbl_add_vendor')]//input[@type='checkbox']";
	public static String manageInv_SaveButton= "SaveButton=#xpath=//button[@type='submit']";


	public static String manageInv_selectedFac= "selectedFac#xpath=//*[@ng-click='selectItem();']";
	public static String manageInv_EditIemHeaderText= "EditIemHeaderText#xpath=//*[@class='headtext']";
	public static String manageInv_VendorsTab = "VendorsTab#lnktext=Vendors";
	public static String manageInv_EditStockStatus= "EditStockStatus#xpath=//*[@name='stock_status']";
	public static String manageInv_EditItem_SaveButton="EditItem_SaveButton#xpath=//button[@type='submit']";
	public static String manageInv_EditItem_GPoContractPrice="EditItem_GPoContractPrice#xpath=//input[@ng-model='v.facilityItemVendors.gpo_contract_price']";
	public static String manageInv_DeleteIcon="DeleteIcon#xpath=//i[@class='glyphicon glyphicon-trash text-danger action-button']";
	public static String manageInv_cancelPopUP="cancelPopUP#xpath=//button[@class='cancel']";
	public static String manageInv_DeletePoUp="DeletePoUp#xpath=//*[@class='sweet-alert showSweetAlert visible']//p";
	public static String manageInv_PrintIcon="PrintIcon#xpath=(//*[@class='glyphicon glyphicon-print text-primary action-button'])[1]";
	public static String manageInv_PrintPreviewButton="PrintPreviewButton#xpath=//*[@ng-click='showPop()']";
	public static String manage_PrintCloseclose="PrintPReviewclose#xpath=//*[@class='fa fa-2x fa-times']";
	public static String manageInv_ItemName="ItemName#xpath=//*[@id='populate_items_pdf']//td[1]";
	public static String manageInv_TransferIcon="TransferIcon#xpath=(//*[@class='glyphicon glyphicon-transfer text-primary'])[1]";
	public static String manageInv_TransferPopUpText="TransferPopUpText#xpath=//*[@class='modal-header']/h4";
	public static String manageInv_PrintPopUpText="PrintPopUpText#xpath=//*[@class='headtext']/span";
	public static String manageInv_InventoryDropDown="InventoryDropDown#xpath=//*[@id='inventory']";
	public static String manageInv_minusSign="minusSign#xpath=//*[@class='glyphicon glyphicon-minus']";
	public static String manageInv_plusSign="plusSign#xpath=//*[@class='glyphicon glyphicon-plus']";
	public static String manageInv_cycleCount="cycleCount#xpath=//*[text()='Cycle Count']";
	public static String manageInv_selectedButton="selectedButton#xpath=//button[text()='Select']";
	//Item Category

	public static String ItemCat_PageLink="PageLink#xpath=//*[@href='#/inventory/item-categories']";
	public static String ItemCat_pageContainerWait="pageContainerWait#xpath=//*[@id='isotopeContainer-']";
	public static String ItemCat_AddCategoryButton="AddCategoryButton#xpath=//*[text()='Add Category']";
	public static String ItemCat_createCatPopUpText="createCatPopUpText#xpath=//*[@class='headtext']/span[1]";
	public static String ItemCat_CatName="CatName#xpath=//*[@id='name']";
	public static String ItemCat_wait="wait#xpath=//*[@id='isotopeContainer-']";
	public static String ItemCat_SAveButton="SAveButton#xpath=//*[@ng-click='saveCategoryFn()']"; 
	public static String ItemCat_SearchTextBox="SearchTextBox#xpath=//*[@id='search']";
	public static String ItemCat_SearchButton="SearchButton#xpath=//*[text()='Search']";
	public static String ItemCat_firstCatName="firstCatName#xpath=//*[@id='isotopeContainer-']//*[@class='grid-heading grid-title-label ng-binding'][1]";
	public static String ItemCat_DeleteButton="DeleteButton#xpath=(//button[text()='Delete'])[1]";
	public static String ItemCat_ConfirmButton="ConfirmButton#xpath=//button[@class='confirm']";
	public static String ItemCat_NoRecordFoundText="NoRecordFoundText#xpath=//*[text()='No records found']";


	//Print Barcodes Page
	public static String PrintBarcodes_PageLink="PageLink#xpath=//*[@href='#/inventory/print-barcodes-qrcodes']";
	public static String PrintBarcodes_wait="waitforForFIrst#xpath=//*[@id='ng-view']";
	public static String PrintBarcodes_firstItem="waitforForFIrst#xpath=(//*[@value='name'])[1]";
	public static String PrintBarcodes_firstItem11="waitforForFIrst#xpath=(//*[@class='table table-striped shopTable'])//tr[1]";
	public static String PrintBarcodes_headerText="headerText#xpath=//*[@class='pagehead']";
	public static String PrintBarcodes_SelectFac_title ="SelectFac_title#xpath=//*[text()='Select Facility']";
	public static String PrintBarcodes_SelectFAc="SelectFac_#xpath=//*[@ng-click='selectItem();']";
	public static String PrintBarcodes_text = "text#xpath=//*[contains(text(),'Print Barcodes/QRCodes for')]";
	public static String PrintBarcodes_searchTextBox="searchTextBox#xpath=//*[@id='search']";
	public static String PrintBarcodes_searchButton="searchButtonx#xpath=//button[text()='Search']";
	public static String PrintBarcodes_skuName="skuName#xpath=//tr[1]/td[3]";
	public static String PrintBarcodes_vendorLabel="PrintBarcodes_vendorLabel#xpath=//*[ng-submit='searchData();']//label[2]";
	public static String PrintBarcodes_vendordropdown="vendordropdown#xpath=//*[@id='vendor']";
	public static String PrintBarcodes_Inventorydropdown="Inventorydropdown#xpath=//*[@id='inventory']";
	public static String PrintBarcodes_InventoryLabel="PrintBarcodes_InventoryLabel#xpath=//*[ng-submit='searchData();']//label[3]";
	public static String PrintBarcodes_PrintCodesButton="PrintCodesButton#xpath=//*[@class='col-sm-18']//button[@type='button']";
	public static String PrintBarcodes_PrintCodesPopUp="PrintCodesPopUp#xpath=//*[@class='popover-content']//span";
	public static String PrintBarcodes_text01="PrintBarcodes_text01#xpath=//*[@class='popover-content']//h4[text()='Print sheet With :']";
	public static String PrintBarcodes_text02="PrintBarcodes_text02#xpath=//*[@class='popover-content']//h4[text()='Generate PDF :']";
	public static String PrintBarcodes_text03="PrintBarcodes_text03#xpath=//*[@class='popover-content']//h4[text()='Print With :']";
	public static String PrintBarcodes_text04="PrintBarcodes_text04#xpath=//*[@class='popover-content']//h4[text()='Print Pages :']";
	public static String PrintBarcodes_PrintPagesTextBox="PrintBarcodes_PrintPagesTextBox#xpath=//label[@for='pages']/input";
	public static String PrintBarcodes_PrintPagesAllRadioButton="PrintPagesAllRadioButton#xpath=//*[@id='all']";
	public static String PrintBarcodes_SelectPagesRadioButton="SelectPagesRadioButton#xpath=//*[@id='pages']";
	public static String PrintBarcodes_PrintButton="PrintButton#xpath=//*[@class='popover-content']//*[@type='button']";
	public static String PrintBarcodes_PreviewPDFTEXT="PreviewPDFTEXT#xpath=//*[@class='modal-header']";
	public static String PrintBarcodes_PrintPReviewclose="PrintPReviewclose#xpath=//*[@class='fa fa-times']";

	//SpecialInstructions

	public static String SI_PageLink="SI#xpath=//*[@href='#/po-special-instructions']";
	public static String SI_TableWait="searchTextBox#xpath=//*[@id='reqListing;]";
	public static String SI_AddButton="AddButton#xpath=//*[@class='pagehead']/button";
	public static String SI_AddPageHeaderText="AddPageHeaderText#xpath=//*[@class='headtext']";
	public static String SI_AddSITextBox="AddSITextBox#xpath=//*[@id='name']";
	public static String SI_SAveButton="SAveButton#xpath=//*[text()='Save']";
	public static String SI_SearchTextBox="SearchTextBox#xpath=//*[@id='search-search']";
	public static String SI_SearchButton="SearchButton#xpath=//*[text()='Search']";
	public static String SI_firstElem="firstElem#xpath=//*[@id='reqListing']//tr/td";
	public static String SI_EditItem="EditItem#xpath=(//*[@class='fa fa-edit'])[1]";
	public static String SI_DeleteButton="DeleteButton#xpath=(//*[@class='fa fa-trash'])[1]";
	public static String SI_closeButton="closeButton#xpath=//*[@ng-click='cancel()']";
	public static String SI_ConfirmButton="ConfirmButton#xpath=//*[@class='confirm']";

	//Manage Physician

	public static String Physician_PageLink="Physician#xpath=//*[@href='#/preference-card/physicians']";
	public static String Physician_AddPhysicianButton="AddPhysicianButton#xpath=//*[text()='Add Physician']";
	public static String SI_firstRow="firstRow#xpath=//*[@id='reqListing_wrapper']";

	//Invoice Payment Term
	public static String Pterms_PageLink="Pterms#xpath=//*[@href='#/invoice/payment-terms']";
	public static String Pterms_waitForTable="waitForTable#xpath=//*[@class='table table-striped']";
	public static String Pterms_AddPTermButton="AddPTermButton#xpath=//*[text()='Add Payment Term']";
	public static String Pterms_addDesc="addDesc#xpath=//*[@id='termDescription']";
	public static String Pterms_addDays="addDays#xpath=//*[@id='paymentTermDays']";
	public static String Pterms_addperc="addperc#xpath=//*[@id='paymentTermPercent']";
	public static String Pterms_addApiID="addApiID#xpath=//*[@id='ApIntegrationId']";
	public static String Pterms_saveButton="saveButton#xpath=//*[@class='modal-footer']/button";
	public static String Pterms_searchTextBox="searchTextBox#xpath=//*[@id='search-search']";
	public static String Pterms_firstItem="firstItem#xpath=//table[@class='table table-striped']//tbody/tr/td";
	public static String Pterms_firstItemAllAttributes="firstItemAllAttributes#xpath=//table[@class='table table-striped']//tbody/tr/td";
	public static String Pterms_EditIcon="EditIcon#xpath=//i[@class='fa fa-edit']";
	public static String Pterms_Deleteicon="Deleteicon#xpath=//i[@class='fa fa-trash-o']";
	public static String Pterms_headerTextEdit="headerTextEdit#xpath=//div[@class='modal-header']//span";
	public static String Pterms_closePopup="closePopup#xpath=//*[@class='modal-footer']//button[2]";
	public static String Pterms_confirmButton="confirmButton#xpath=//*[@class='confirm']";
	public static String Pterms_OKbutton="OKbutton#xpath=//*[@class='confirm']";
	//News

	public static String News_PageLink="News_PageLink#xpath=//*[@href='#/admin/news']";
	public static String News_addNews="addNews#xpath=//*[@class='pagehead']//button[@ng-click='createNews()']";
	public static String News_addTitle="addTitle#xpath=//*[@id='title']";
	public static String News_addDec="addDec#xpath=//*[starts-with(@id,'taTextElement')]";
	public static String News_ActiveButtn="ActiveButtn#xpath=//*[@class='bootstrap-switch-container']//span[1]";
	public static String News_InActiveButtn="InActiveButtn#xpath=//*[@class='bootstrap-switch-container']//span[@class='bootstrap-switch-handle-off bootstrap-switch-default']";
	public static String News_ActiveInactiveClass="ActiveInactiveClass#xpath=//*[@class='col-sm-6 user-action-switch']/div[1]";
	public static String News_saveButton="saveButton#xpath=//*[@class='pagehead']//button[1]";
	public static String News_waitForNews="waitForNews#xpath=//*[@id='isotopeContainer-']";
	public static String News_searchTextBox="searchTextBox#xpath=//*[@id='search-search']";
	public static String News_searchButton="searchButton#xpath=//button[text()='Search']";
	public static String News_firstNews="firstNews#xpath=//*[@id='isotopeContainer-']//*[@class='grid-heading grid-title-label ng-binding']";
	public static String News_EditNews="EditNews#xpath=//*[@ng-click='editNews(NewsData.id)']";
	public static String News_EditNewsText="EditNewsText#xpath=//*[@class='pagehead']/span[@ng-show='isEditMode']";

	//Vendor
	public static String Vendor_PageLink="Vendor_PageLink#xpath=//*[@href='#/admin/vendor']";	
	public static String Vendor_firstItem="Vendor_PageLink#xpath=(//*[@class='grid-heading grid-title-label ng-binding'])[1]";
	public static String Vendor_searchTextBox="searchTextBox#xpath=//*[@id='search-search']";
	public static String Vendor_searchButton="searchButton#xpath=//button[text()='Search']";
	public static String Vendor_waitForVendor="waitForVendor#xpath=//*[@id='isotopeContainer-']";
	public static String Vendor_status="Vendor_status#xpath=(//*[@class='pull-left']/span)[1]";
	public static String Vendor_EditButton="Vendor_EditButton#xpath=(//*[@class='grid-row-last']//button)";
	public static String Vendor_EditVendorPopup="Vendor_EditVendorPopup#xpath=//*[@class='headtext']/span[1]";
	public static String Vendor_waitForTabs="waitForTabs#xpath=//ul[@class='nav nav-tabs']";

	//PriceTier Setup

	public static String priceTier_PageLink="priceTier_PageLink#xpath=//*[@href='#/admin/pricetier']";	
	public static String priceTier_addPriceTier="addPriceTier#xpath=//*[@class='pagehead']//button[@ng-click='createPriceTier()']";
	public static String priceTier_addName="addName#xpath=//*[@id='tiername']";
	public static String priceTier_addpercentage="addpercentage#xpath=//*[@id='price_percentage']";
	public static String priceTier_saveButton="saveButton#xpath=//*[@class='modal-footer']//button[1]";
	public static String priceTier_searchTextBox="searchTextBox#xpath=//*[@id='search-search']";
	public static String priceTier_waitfor="waitfor#xpath=//*[@id='reqListing']";
	public static String priceTier_firstItem="firstItem#xpath=//*[@id='reqListing']//tr[1]//td[1]";
	public static String priceTier_EditButton="EditButton#xpath=//i[@class='fa fa-edit']";
	public static String priceTier_vendorSetup="vendorSetup#xpath=//*[@class='pagehead']//button[@ng-click='setVendorPriceTier()']";
	public static String priceTier_vendorPriceSetup="Vendor_vendorPriceSetup#xpath=//*[@class='headtext']/span[1]";
	public static String priceTier_DeleteButton="DeleteButton#xpath=//i[@class='fa fa-trash']";
	public static String priceTier_confirmButton="confirmButton#xpath=//*[@class='confirm']";
	public static String priceTier_OKbutton="OKbutton#xpath=//*[@class='confirm']";

	//Inventory Location
	public static String InvLoc_PageLink="InvLoc_PageLink#xpath=//*[@href='#/inventory-locations']";
	public static String InvLoc_AddINvLOc="AddINvLOc#xpath=//*[@class='pagehead']//button[text()='Add Inventory Location']";
	public static String InvLoc_addName="addName#xpath=//input[@id='desc']";
	public static String InvLoc_waitfortable="waitfortable#xpath=//*[@id='reqListing']";
	public static String InvLoc_addFacility="addFacility#xpath=//*[@name='facility']";
	public static String InvLoc_InactiveCreateButton="InactiveCreateButton#xpath=//*[@class='modal-footer']//button[1]";
	public static String InvLoc_searchTextBox="searchTextBox#xpath=//*[@id='search-search']";
	public static String InvLoc_firstInventoryLoc="firstInventoryLoc#xpath=//*[@id='reqListing']//tbody//span[1]";
	public static String InvLoc_firstPlusIcon="firstPlusIcon#xpath=//*[@id='reqListing']//tbody//button[1]";
	public static String InvLoc_Added_INvenLoc="Added_INvenLoc#xpath=//ul[@class='facility_department_sublist']";
	public static String InvLoc_EditFacIcon="EditFacIcon#xpath=//i[@class='fa fa-edit']";
	public static String InvLoc_DeleteFacIcon="DeleteFacIcon#xpath=//*[@class='fa fa-trash-o']";
	public static String InvLoc_confirmButton="confirmButton#xpath=//*[@class='confirm']";

	//Manage Inventory
	public static String AppPage_PageLink="AppPage_PageLink#xpath=//*[@href='#/approvals']";
	public static String AppPage_headerText="headerText#xpath=//*[@class='pagehead']";

	//Manufacturer
	public static String manufacturer_PageLink="manufacturer_PageLink#xpath=//*[@href='#/admin/manufacturers']";
	public static String manufacturer_headerText="headerText#xpath=//*[@class='pagehead']";
	public static String manufacturer_wait="wait#xpath=//*[@id='isotopeContainer-']";
	public static String manufacturer_3rdVendor="3rdVendor=#xpath=//*[@id='isotopeContainer-']//*[@class='grid-item ng-scope'][3]";
	public static String manufacturer_searchTextBox="searchTextBox#xpath=//*[@id='search-search']";
	public static String manufacturer_searchButton="searchButton#xpath=//button[@type='submit']";
	public static String manufacturer_1stVendor="1stVendor=#xpath=//*[@id='isotopeContainer-']//*[@class='grid-item ng-scope'][1]";

	//GL Code
	public static String glCode_PageLink="glCode_PageLink#xpath=//*[@href='#/admin/glcode']";
	public static String glCode_wait="wait#xpath=//*[@id='isotopeContainer-']";
	public static String glCode_addGlCOde="addGlCOde#xpath=//*[@class='pagehead']//*[@ng-click='createGLCode()']";
	public static String glCode_addcode="addCode#xpath=//*[@id='code']";
	public static String glCode_addName="addName#xpath=//*[@id='name']";
	public static String glCode_addDesc="addDesc#xpath=//*[@id='description']";
	public static String glCode_saveButton="saveButton#xpath=//*[@class='modal-footer']//button[1]";
	public static String glCode_searchTextBox="searchTextBox#xpath=//*[@id='search-search']";
	public static String glCode_searchButton="searchButton#xpath=//button[@type='submit']";
	public static String glCode_firstGlCOde="firstGlCode#xpath=//*[@id='isotopeContainer-']//*[@class='grid-heading grid-title-label ng-binding'][1]";
	public static String glCode_deleteButton="deleteButton#xpath=(//*[@class='pull-right']//button[@ng-click='deleteGLCode(GLCodeData.id)'])[1]";
	public static String glCode_editButton="editButton#xpath=(//*[@class='pull-right']//button[@ng-click='editGLCode(GLCodeData.id)'])[1]";

	//Pattern
	public static String pattern_PageLink="pattern_PageLink#xpath=//*[@href='#/admin/pattern']";
	public static String pattern_wait="wait#xpath=//*[@id='reqListing_wrapper']";
	public static String pattern_addPattern="addPattern#xpath=//*[@class='pagehead']//*[@ng-click='createPattern()']";
	public static String pattern_addName="addName#xpath=//*[@id='name']";
	public static String pattern_selectFromTemplate="selectFromTemplate#xpath=//*[@id='sel_template']";
	public static String pattern_initialValue="initialValue#xpath=//*[@name='tpl_initial_value']";

	public static String pattern_plusIcon="plusIcon#xpath=//*[@id='btn_add_template']";
	public static String pattern_saveButton="saveButton#xpath=//*[@class='modal-footer']//button[1]";
	public static String pattern_firstPattern="firstPattern#xpath=//*[@id='reqListing']//tr[1]//td[1]";
	public static String pattern_deleteButton="deleteButton#xpath=(//*[@class='fa fa-trash'])[1]";
	public static String pattern_editButton="editButton#xpath=(//i[@class='fa fa-edit'])[1]";
	public static String pattern_gearButton="gearButton#xpath=(//*[@class='fa fa-gear'])[1]";
	public static String pattern_IncreasedpatternValue="IncreasedpatternValue#xpath=//*[@class='modal-body']//h1";
	public static String pattern_confirmButton="confirmButton#xpath=//*[@class='confirm']";

	    // Checkout
	    public static String Checkout_Btn ="CheckoutBtn#xpath=//*[@ng-click='gotoCart()']";
	    
	    // Price Change Details
	    public static String PriceChangeDetails_text = "PriceChangeText#xpath=//*[text()='Price Change Details']";
	    public static String PriceChangeDetails_current_text = "CurrentPricetext#xpath=//strong[text()='Current Price']";
	    public static String PriceChangeDetails_Close = "close#xpath=//*[text()='Close']";
	    
	    // Purchace History
	    public static String purchaseHistory_itemPurchace_text = "itemPurchaceText#xpath=//*[contains(text(),'Item Purchase History')]";
	    
	    // Edit Item Page
	    public static String EditItem_text = "EditItem#xpath=//*[text()='Edit Item']";
	    
	
		public static String Shop_drilldownRemoveLayout = "drilldownRemoveLayout#xpath=//a[contains(text(),'---automation layout---')]/../i";
			// admin
		public static String Admin_Vendor = "Vendor#xpath=//*[@href='#/admin/vendor']";
		public static String Admin_vendoraccountsetup = "vendoraccountsetup#xpath=//a[@href='#/admin/vendoraccountsetup']";

	
			  // Chat 
	    public static String Chat ="Chat#xpath=//*[@class='chat-directive']//em";
	    public static String Chat_Customerservice_txt ="Customerservice_txt#xpath=//*[contains(text(),'Customer Service')]";
	    public static String Chat_input ="ChatIntput#xpath=//*[@id='chat-textarea']";
	    public static String Chat_SendBtn ="ChatSendBtnIntput#xpath=//*[@id='btn-chat']";
	    
	    
	    // Order
	    public static String Order ="Myorder#xpath=//*[@href='#/orders']";
	    public static String Order_Myorder_txt ="Myorder_txt#xpath=//div[contains(text(),'My Orders')]";
	    public static String Order_Search_txt ="SearchTxt#xpath=//label[text()='Search:']";
	    public static String Order_Search_Input ="SearchInput#xpath=//*[@id='PONUM']";
	    public static String Order_Vendor_txt ="VendorTxt#xpath=//*[@for='vendor']";
	    public static String Order_Vendor_dropdown ="Vendoe_dropdown#xpath=//*[@id='vendor']//*[@class='ui-select-match-text pull-left']";
	    public static String Order_status_txt ="statusTxt#xpath=//*[@for='status']";
	    public static String Order_type_txt ="typeTxt#xpath=//*[@for='type']";
	    public static String Order_type_dropdown ="type_dropdown#xpath=//*[@id='type']//*[@class='ui-select-match-text pull-left']";
	    public static String Order_facility_txt ="facilityTxt#xpath=//*[@for='facility']";
	    public static String Order_facility_dropdown ="facility_dropdown#xpath=//*[@id='facility']//*[@class='ui-select-match-text pull-left']";
	    public static String Order_depts_txt ="deptsTxt#xpath=//*[@for='depts']";
	    public static String Order_depts_dropdown ="depts_dropdown#xpath=//*[@id='depts']//*[@class='ui-select-match-text pull-left']";
	    public static String Order_creater_txt ="createrTxt#xpath=//*[@for='creater']";
	    public static String Order_creater_dropdown ="creater_dropdown#xpath=//*[@id='creater']//*[@class='ui-select-match-text pull-left']";
	    public static String Order_SearrchBtn ="Search_Btn#xpath=//*[@type='submit']";
	    
	    public static String Order_PO_ID_First ="POId#xpath=(//*[@ng-repeat='order in ordersData.purchaseOrders']//td/a)[1]";
	    public static String Order_PO_Header ="PO_Header#xpath=//*[@class='pagehead ng-binding']";
	    public static String Order_PO_Notes_Icon ="PO_Notes_Btn#xpath=//*[@class='ng-scope fa fa-sticky-note-o']";
	    public static String Order_PO_Notes_HeaderTxt ="PO_Notes_HeaderTxt#xpath=//*[@class='headtext']//*[@class='ng-binding']";
	    public static String Order_PO_Notes_AddIcon_btn ="PO_Notes_Addbtn#xpath=//*[text()='Add New Note']";
	    public static String Order_PO_Notes_Input ="PO_Notes_Input#xpath=(//textarea)[1]";
	    public static String Order_PO_Notes_AddBtn ="AddBtn#xpath=//*[text()='Add']";
	    public static String Order_PO_Notes_DeleteBtn ="DeleteBtn#xpath=(//*[@class='fa fa-trash'])[1]";
	    public static String Order_PO_Notes_Deletevalidation ="Deletevalidation#xpath=//*[text()='Are you sure you want to delete this note?']";
	    public static String Order_PO_Notes_Delete_yes = "Delete_yes#xpath=//*[@class='confirm']";
	    public static String Order_PO_Notes_CloseBtn ="CloseBtn#xpath=//*[text()='Close']";
	    public static String Order_PO_Notes_Add_HeaderTxt ="PO_Notes_HeaderTxt#xpath=(//*[@class='headtext']//*[@class='ng-binding'])[1]";
	    
	    public static String Order_PO_Doc_AfterUploadFiles ="Items#xpath=(//*[@ng-repeat='item in rows'])[1]";
	
	    public static String OrderNotes_PO_First ="PO_NotesFirst#xpath=(//*[@ng-click='openPONote(order)']/i)[1]";
	    public static String OrderNotes_PO_NotesCount ="NotesCount#xpath=(//*[@ng-click='openPONote(order)']//sup)[1]";
	    public static String OrderNotes_PO_NotesClose ="Close#xpath=(//button[@ng-click='cancel()']/i)[1]";
	    
	    public static String OrderDoc_PO_First ="PO_DocFirst#xpath=(//*[@ng-click='attachFiles(order)']/i)[1]";
	    public static String OrderDoc_PO_NotesCount ="DocCount#xpath=(//*[@ng-click='attachFiles(order)']//sup)[1]";
	    public static String OrderDoc_PO_Doc_Icon ="PO_Doc_Btn#xpath=//i[@class='ng-scope fa fa-paperclip']";

	    // order details
	    public static String OrderDetails_PO_getQty ="PO_getQty#xpath=//i[@class='ng-scope fa fa-paperclip']";
	    public static String OrderDetails_PO_Qty_Increase ="Qty_Increase#xpath=(//*[@ng-click='incrementQty(poItem.item_vendor_id, orderData.order.id,poItem.requested_qty, false)'])[1]";
	    public static String OrderDetails_PO_Qty_Decreasse ="Qty_Decreasse#xpath=(//*[@ng-click='decrementQty(poItem.item_vendor_id, orderData.order.id,poItem.requested_qty, false)'])[1]";
	    public static String OrderDetails_PO_Dropdown ="PO_Dropdown#xpath=//button[@ng-click='getInvoiceData(orderData.order);']";
	    public static String OrderDetails_PO_Dropdown_AddItemsTocart ="Dropdown_AddItemsTocart#xpath=//*[@ng-click='AddOrderItemsTOCart();']";
	    public static String OrderDetails_Btn_GeneratePo ="GeneratePo#xpath=//*[@tour-step-title='Cart: More Options']/button";
	    public static String OrderDetails_Btn_ClearAllCart ="ClearAllCart#xpath=//a[@ng-click='clearCart()']";
	    public static String OrderDetails_DeleteConfirmion="DeleteConfirmion#xpath=//*[text()='Are you sure you want to clear cart?']";
	    public static String OrderDetails_DeleteSucessConfirmion="DeleteSucessConfirmion#xpath=//*[text()='Cart cleared successfully.']";
	    public static String OrderDetails_HeaderDropdown="HeaderDropdown#xpath=//*[@tour-step='order-detail-more']/button";
	    public static String OrderDetails_AddOrderItemsTOCart="AddOrderItemsTOCart#xpath=//a[@ng-click='AddOrderItemsTOCart();']";
	    public static String OrderDetails_AddInvoice="AddInvoice#lnktext=Add Invoice";
	    public static String Confirmionok="Confirmionok#xpath=//*[@class='confirm' and text()='OK']";
	    public static String OrderDetails_PrintItems="PrintItems#xpath=//*[text()='Print Items']";
	    public static String OrderDetails_OrderItems="OrderItems#xpath=//*[text()='Order Items']";
	    public static String OrderDetails_POApprovalLog="POApprovalLog#xpath=//*[text()='PO Approval Log']";
	    public static String OrderDetails_DeletePO="DeletePO#xpath=//a[text()='Delete PO']";
	    public static String OrderDetails_ShowTour="ShowTour#xpath=//a[text()='Show Tour']";
	    public static String OrderDetails_Dept="Dept#xpath=//p/vendor-info/span";
	    public static String OrderDetails_Physision="Physision#xpath=(//select[@ng-model='poItem.physician_id'])[1]";
	    public static String OrderDetails_Dept1="Dept#xpath=(//select[@ng-model='poItem.department_id'])[1]";
	    public static String OrderDetails_Close="Close#xpath=//span[@id='btnAdd' and contains(text(),'Close')]";    
	    public static String OrderDetails_MyOrderTxt="MyOrderTxt#xpath=//div[contains(text(),' My Orders')]";
	    
	    
	    // Templates
	    public static String Templates="Templates#xpath=//*[text()='Templates']";
	    public static String Templates_AddBtn="Add#xpath=//*[@ng-click='createTemplate()']";
	    public static String Templates_AddTemplatetxt="AddTemplatetxt#xpath=//*[@class='headtext']//*[text()='Add Template']";
	    public static String Templates_Name="Name#xpath=//*[@id='tempName']";
	    public static String Templates_CreateTemplate="CreateTemplate#xpath=//*[text()='Create Template']";
	    public static String Templates_Cancel="Cancel#xpath=//*[@class='modal-footer']//button[text()='Cancel']";
	    public static String Templates_Edit="Edit#xpath=//*[@class='fa fa-edit']";
	    public static String Templates_EditName="EditName#xpath=//form/div/input";
	    public static String Templates_EditSubmit="EditSubmit#xpath=//*[@title='Submit']";
	    public static String Templates_Editview="Editview#xpath=//*[@onbeforesave='updateTemplate()']";
	    public static String Templates_SearchScan="SearchScan#xpath=//*[@id='searchInput']";
	    public static String Template_Add_First = "Add#xpath=(//*[@id='btnAdd'])[1]";
	    
	    public static String Template_Schedule = "Schedule#xpath=//a//strong[text()='Schedule']";
	    public static String Template_AddSchedule = "AddSchedule#xpath=//button[text()='Add Schedule']";
	    public static String Template_Schedule_Every = "Schedule_Every#xpath=//select[@ng-options='item.value as item.label for item in frequency']/option";
	    public static String Template_Schedule_Savebtn = "Savebtn#xpath=(//button[text()='Save'])[1]";
	    public static String Template_Schedule_Deletebtn = "Deletebtn#xpath=//a[@uib-tooltip='Delete']";
	    public static String Template__Deletevalidation = "validation#xpath=//*[text()='Are you sure?']";
	    public static String Template__Deletevalidation1 = "validation#xpath=//*[text()='Are you sure you want to delete this schedule ?']";
	    public static String Template__Deletetemplatevalidation = "validation#xpath=//*[text()='Are you sure you want to delete this template ?']";
	    public static String Template_Yes = "Yes#xpath=//button[text()='Yes']";
	    public static String Template_Cancel = "Cancel#xpath=//button [text()='Cancel']";
	    public static String Template_TostSucess = "TostSucess#xpath=//*[@class='toast-title']";
	    public static String Template_TostMsg = "Tostmsg#xpath=//*[@class='toast-message']";
	    public static String Template_Search_Input ="SearchInput#xpath=//*[@id='PONUM']";
	    public static String Template_SearchBtn ="SearchBtn#xpath=//button[text()='Search']";
	    public static String Template_SaveBtn ="SaveBtn#xpath=//button[text()='Save']";
	    public static String Template_CloseBtn ="Close#xpath=//button[text()='Close']";
	    public static String Template_Color ="TemplateColor#xpath=(//*[@class='item-info-icon-padd']//*[@data-toggle='tooltip'])[1]";
	    public static String Template_ShareYes ="ShareYes#xpath=//*[@class='bootstrap-switch-container']//*[text()='Yes']";
	    public static String Template_ShareNo ="ShareNo#xpath=//*[@class='bootstrap-switch-container']//*[text()='No']";
	    public static String Template_Edit ="Edit#xpath=(//i[@class='fa fa-edit'])[1]";
	    public static String Template_Delete ="Delete#xpath=(//i[@class='fa fa-trash-o'])[1]";
	    public static String Template_Use ="Use#xpath=(//a[@uib-tooltip='Use'])[1]";
	    public static String Template_GeneratePO ="GeneratePO#xpath=//*[@id='btnAdd']//*[@id='btnAdd']";
	    public static String Template_Warning ="Warning#xpath=//h2[text()='Warning']";
	    public static String Template_WarningMsg ="WarningMsg#xpath=//*[text()='Increase qty. of at least one item to generate the PO.']";
	    public static String Template_Warningok ="ok#xpath=//button[text()='OK']";
	    public static String Template_Plus ="Plus#xpath=//button[text()='+']";
	    //PLANNER
	    
	    // REport
	    public static String Report="Report#xpath=//a[@href='#/reports/activity-report']";
	    public static String Report_ViewMoreREports="ViewMoreREports#xpath=//button[contains(text(),'View More Reports')]";
	    
	    // Preferred card
	    public static String preferenceCard="preferenceCard#xpath=//*[@href='#/preference-card/cards']";	
	    public static String preferenceCard_AddPreferenceCard="AddPreferenceCard#xpath=//a[@href='#/preference-card/card/create']";
	    public static String preferenceCard_AddStage="AddStage#xpath=//*[@value='Add Stage']";
	    public static String preferenceCard_InputAddStage="InputAddStage#xpath=//*[@id='prefCardStage']";
	    public static String preferenceCard_BtnAddStage="BtnStage#xpath=//button[text()='Add Stage']";
	    public static String preferenceCard_Reorder="Reorder#xpath=//button[text()='Reorder items']";
	    public static String preferenceCard_SearchItem="SearchItem#xpath=//*[@id='searchInput']";
	    public static String preferenceCard_AddItem="AddItem#xpath=//button[@id='btnAdd']";
	    
	    // Paitent
	    public static String Paintet="Paintet#xpath=//*[@href=\"#/preference-card/patients\"]";
	    public static String Paintet_First="FirstPaintet#xpath=(//button[contains(text(),'+')])[1]";
	    public static String Paintet_FirstPhysician="FirstPaintetPhysician#xpath=(//*[@ng-repeat-start='patient in patients']/td)[1]";
	    
	    // cases
	    public static String Cases ="Cases#xpath=//a[@href='#/preference-card/cases']";
	    public static String Cases_dateRange ="dateRange#xpath=//*[@id='dateRange']";
	    public static String Cases_searchbutton="searchbutton#xpath=//button[@type='submit']";
	    
	    
	    public static String PaginationLast="PaginationLast#xpath=//*[@class='pagination-last ng-scope']/a";

	
}







