package mod.adrenix.nostalgic.util.common.lang;

import mod.adrenix.nostalgic.util.common.annotation.PublicAPI;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public interface Lang
{
    /* Static */

    @PublicAPI Translation TITLE = new Translation("gui.nostalgic_tweaks.title");
    @PublicAPI Translation EMPTY = new Translation("gui.nostalgic_tweaks.empty");

    /* Functional */

    /**
     * Get a lang key from a string. If a translation exists for the given string, then the key will retrieve that
     * string; otherwise, the literal form of the string will be returned. This is useful in situations where a lang key
     * is required, but the string may not have a translation, such as a version number.
     *
     * @param literal A literal string.
     * @return A new {@link Translation} instance.
     */
    @PublicAPI
    static Translation literal(String literal)
    {
        return new Translation(literal);
    }

    /**
     * Get a lang key from a {@link Component}. This is useful in situations where a lang key is required, but only a
     * component instance is available for translation.
     *
     * @param component A {@link Component} instance.
     * @return A new {@link Translation} instance.
     */
    @PublicAPI
    static Translation component(Component component)
    {
        return new Translation(DecodeLang.findAndReplace(component).getString());
    }

    /* Keybindings */

    /**
     * Lang keys that provide translations for keybindings.
     */
    interface Binding
    {
        Translation OPEN_CONFIG = new Translation("key.nostalgic_tweaks.open_config");
        Translation TOGGLE_FOG = new Translation("key.nostalgic_tweaks.toggle_fog");
        Translation UNBOUND = new Translation("key.nostalgic_tweaks.unbound");
    }

    /* Vanilla */

    /**
     * Lang keys that reference the default vanilla language file.
     */
    interface Vanilla
    {
        Translation INVENTORY = new Translation("container.inventory");
        Translation GENERAL = new Translation("stat.generalButton");
        Translation SEARCH = new Translation("gui.socialInteractions.search_hint");
        Translation GUI_ADVANCEMENTS = new Translation("gui.advancements");
        Translation GUI_CANCEL = new Translation("gui.cancel");
        Translation GUI_STATS = new Translation("gui.stats");
        Translation GUI_DONE = new Translation("gui.done");
        Translation READ_WORLD_DATA = new Translation("selectWorld.data_read");
        Translation MENU_SINGLEPLAYER = new Translation("menu.singleplayer");
        Translation MENU_MULTIPLAYER = new Translation("menu.multiplayer");
        Translation MENU_DISCONNECT = new Translation("menu.disconnect");
        Translation MENU_OPTIONS = new Translation("menu.options");
        Translation MENU_RETURN_TO_GAME = new Translation("menu.returnToGame");
        Translation MENU_RETURN_TO_TITLE = new Translation("menu.returnToMenu");
        Translation MENU_QUIT = new Translation("menu.quit");
        Translation MENU_GAME = new Translation("menu.game");
        Translation MENU_LAN = new Translation("menu.shareToLan");
        Translation SAVE_LEVEL = new Translation("menu.savingLevel");
    }

    /* Screens */

    /**
     * Lang keys that are used by the mod's homepage settings screen.
     */
    interface Home
    {
        Translation TITLE = from("title");
        Translation KOFI = from("kofi");
        Translation DISCORD = from("discord");
        Translation GOLDEN_DAYS = from("golden_days");
        Translation SUPPORTERS = from("supporters");
        Translation CONNECTING = from("connecting");
        Translation DISCONNECTED = from("disconnected");
        Translation WRONG_VERSION = from("wrong_version");
        Translation INVALID_DATA = from("invalid_data");
        Translation KOFI_MEMBER = from("kofi_member");
        Translation MOD_CREATOR = from("mod_creator");
        Translation FPS_INFO = from("fps_info");
        Translation FPS_SWITCH = from("fps_switch");
        Translation DEBUG = from("debug");
        Translation DEBUG_INFO = from("debug_info");
        Translation DEBUG_SWITCH = from("debug_switch");
        Translation DEBUG_SHORTCUT = from("debug_shortcut");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.screen.home." + name);
        }
    }

    /**
     * Lang keys that are used by affirmation screens.
     */
    interface Affirm
    {
        Translation QUIT_TITLE = from("quit_title");
        Translation QUIT_BODY = from("quit_body");
        Translation QUIT_DISCARD = from("quit_discard");
        Translation QUIT_CANCEL = from("quit_cancel");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.screen.affirm." + name);
        }
    }

    /**
     * Lang keys that are used in the game's level loading screens.
     */
    interface Level
    {
        Translation LOADING = from("loading");
        Translation BUILDING = from("building");
        Translation SIMULATE = from("simulate");
        Translation SAVING = from("saving");
        Translation ENTER_NETHER = from("enterNether");
        Translation ENTER_END = from("enterEnd");
        Translation LEAVING_NETHER = from("leaveNether");
        Translation LEAVING_END = from("leaveEnd");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.screen.level." + name);
        }
    }

    /**
     * Lang keys that are used in the game's title screen.
     */
    interface Title
    {
        Translation MODS = from("mods");
        Translation MODS_TEXTURE = from("mods_texture");
        Translation TEXTURE_PACK = from("texture_pack");
        Translation TUTORIAL = from("tutorial");
        Translation COPYRIGHT_ALPHA = from("copyright_alpha");
        Translation COPYRIGHT_BETA = from("copyright_beta");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.screen.title." + name);
        }
    }

    /**
     * Lang keys that are used in the game's pause screen.
     */
    interface Pause
    {
        Translation GAME = from("game");
        Translation ACHIEVEMENTS = from("achievements");
        Translation RETURN_LOWER = from("return");
        Translation SAVE_LOWER = from("save");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.screen.pause." + name);
        }
    }

    /**
     * Lang keys that are used by the mod's toast system.
     */
    interface Toast
    {
        Translation WELCOME_TITLE = from("welcome_title");
        Translation WELCOME_MESSAGE = from("welcome_message");
        Translation TWEAK_S2C_TITLE = from("tweak_s2c_title");
        Translation TWEAK_S2C_MESSAGE = from("tweak_s2c_message");
        Translation TWEAK_C2S_TITLE = from("tweak_c2s_title");
        Translation TWEAK_C2S_MESSAGE = from("tweak_c2s_message");
        Translation HANDSHAKE_TITLE = from("handshake_title");
        Translation HANDSHAKE_MESSAGE = from("handshake_message");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.toast." + name);
        }
    }

    /* Widgets */

    /**
     * Lang keys that are used by input widgets.
     */
    interface Input
    {
        Translation SEARCH = from("search");
        Translation INVALID_TAG = from("search.invalid_tag");
        Translation TYPE = from("type");
        Translation TIP = from("tip");
        Translation TIP_CLICK = from("tip.click");
        Translation TIP_SEARCH = from("tip.search");
        Translation COPY = from("copy");
        Translation COPY_INFO = from("copy.info");
        Translation PASTE = from("paste");
        Translation PASTE_INFO = from("paste.info");
        Translation CLEAR = from("clear");
        Translation CLEAR_INFO = from("clear.info");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.input." + name);
        }
    }

    /**
     * Lang keys that are used by slider widgets.
     */
    interface Slider
    {
        Translation CAP = from("cap");
        Translation STACK = from("stack");
        Translation LIMIT = from("limit");
        Translation FILES = from("files");
        Translation SPEED = from("speed");
        Translation CUSTOM = from("custom");
        Translation RADIUS = from("radius");
        Translation OFFSET = from("offset");
        Translation DENSITY = from("density");
        Translation ENCROACH = from("encroach");
        Translation INTENSITY = from("intensity");
        Translation BLOCK_LIGHT = from("block_light");
        Translation Y_LEVEL = from("y_level");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.slider." + name);
        }
    }

    /**
     * Lang keys that are used by button widgets.
     */
    interface Button
    {
        Translation SAVE = from("save");
        Translation MANAGE = from("manage");
        Translation DOWNLOAD = from("download");
        Translation RECONNECT = from("reconnect");
        Translation IMPORT_AND_EXPORT = from("import_and_export");
        Translation SERVER_OPERATIONS = from("server_operations");
        Translation TOGGLE_ALL_TWEAKS = from("toggle_all_tweaks");
        Translation REVIEW_CHANGES = from("review_changes");
        Translation LOGICAL_SIDE = from("logical_side");
        Translation CREATE_BACKUP = from("create_backup");
        Translation CLIENT_BACKUP = from("client_backup");
        Translation SERVER_BACKUP = from("server_backup");
        Translation CLIENT_IMPORT = from("client_import");
        Translation CLIENT_EXPORT = from("client_export");
        Translation CLIENT_RELOAD = from("client_reload");
        Translation SERVER_IMPORT = from("server_import");
        Translation SERVER_EXPORT = from("server_export");
        Translation SERVER_RELOAD = from("server_reload");
        Translation VIEW_BACKUPS = from("view_backups");
        Translation OPEN_FOLDER = from("open_folder");
        Translation DISABLE_ALL = from("disable_all");
        Translation DELETE_ALL = from("delete_all");
        Translation ENABLE_ALL = from("enable_all");
        Translation EDIT_LIST = from("edit_list");
        Translation FAVORITE = from("favorite");
        Translation OVERRIDE = from("override");
        Translation NETWORK = from("network");
        Translation INSPECT = from("inspect");
        Translation DISABLE = from("disable");
        Translation ENABLE = from("enable");
        Translation REMOVE = from("remove");
        Translation REFRESH = from("refresh");
        Translation SEE_ALL = from("see_all");
        Translation STATUS = from("status");
        Translation DELETE = from("delete");
        Translation FILTER = from("filter");
        Translation EXPORT = from("export");
        Translation IMPORT = from("import");
        Translation LOCAL = from("local");
        Translation QUICK = from("quick");
        Translation RESET = from("reset");
        Translation APPLY = from("apply");
        Translation COPY = from("copy");
        Translation EDIT = from("edit");
        Translation HELP = from("help");
        Translation OKAY = from("okay");
        Translation UNDO = from("undo");
        Translation REDO = from("redo");
        Translation ADD = from("add");
        Translation YES = from("yes");
        Translation NO = from("no");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.button." + name);
        }
    }

    /**
     * Lang keys that are used by tooltip bubbles.
     */
    interface Tooltip
    {
        Translation HIDE = from("hide");
        Translation SHIFT = from("shift");
        Translation QUICK = from("quick");
        Translation MANAGE = from("manage");
        Translation FILTER = from("filter");
        Translation HOME_DEBUG = from("home_debug");
        Translation HOME_SUPPORTERS = from("home_supporters");
        Translation FILTER_DISABLED = from("filter_disabled");
        Translation FAVORITE_DISABLED = from("favorite_disabled");
        Translation FAVORITE = from("favorite");
        Translation ALL = from("all");
        Translation ALL_DISABLED = from("all_disabled");
        Translation NOT_OPERATOR = from("not_operator");
        Translation NOT_CONNECTED_OR_OPERATOR = from("not_connected_or_operator");
        Translation FINISH = from("finish");
        Translation COPY = from("copy");
        Translation UNDO = from("undo");
        Translation SAVE = from("save");
        Translation ADD = from("add");
        Translation SAVE_SSO = from("save_sso");
        Translation SAVE_MAX_BACKUP = from("save_max_backup");
        Translation SAVE_TWEAK_LOCAL = from("save_tweak_local");
        Translation SAVE_TWEAK_NETWORK = from("save_tweak_network");
        Translation SAVE_BACKUP = from("save_backup");
        Translation EDIT_BACKUP = from("edit_backup");
        Translation DELETE_BACKUP = from("delete_backup");
        Translation INSPECT_BACKUP = from("inspect_backup");
        Translation DOWNLOAD_BACKUP = from("download_backup");
        Translation APPLY_CLIENT_BACKUP = from("apply_client_backup");
        Translation APPLY_SERVER_BACKUP = from("apply_server_backup");
        Translation OPEN_BACKUP_FOLDER = from("open_backup_folder");
        Translation REFRESH_VIEW = from("refresh_view");
        Translation REVIEW_CHANGES = from("review_changes");
        Translation DELETE_ALL_BACKUPS = from("delete_all_backups");
        Translation OPEN_DISCORD = from("open_discord");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.tooltip." + name);
        }
    }

    /**
     * Lang keys that lists use within the graphical user interface.
     */
    interface Listing
    {
        Translation ADD = from("add");
        Translation WILDCARD_TITLE = from("wildcard_title");
        Translation WILDCARD_MESSAGE = from("wildcard_message");
        Translation WILDCARD_ALERT = from("wildcard_alert");
        Translation INVALID_ITEM = from("invalid_item");
        Translation INVALID_TYPE = from("invalid_type");
        Translation INVALID_MESSAGE = from("invalid_message");
        Translation ALREADY_ADDED = from("already_added");
        Translation NOTHING_FOUND = from("nothing_found");
        Translation NOTHING_SAVED = from("nothing_saved");
        Translation EMPTY_FAVORITES = from("empty_favorites");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.listing." + name);
        }
    }

    /**
     * Lang keys that are used by rows within row-list widgets.
     */
    interface TweakRow
    {
        Translation ENABLED = from("enabled");
        Translation DISABLED = from("disabled");
        Translation CACHE = from("cache");
        Translation CACHE_CLIENT = from("cache_client");
        Translation NETWORK_DISCONNECTED = from("network_disconnected");
        Translation STAR_OFF = from("star_off");
        Translation STAR = from("star");
        Translation RESET = from("reset");
        Translation RESET_OFF = from("reset_off");
        Translation STATUS = from("status");
        Translation DESCRIPTION = from("description");
        Translation NO_IMPL = from("no_impl");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.row.tweak." + name);
        }
    }

    /**
     * Lang keys that are used by tweak tag widgets.
     */
    interface Tag
    {
        Translation CONFLICT = from("conflict");
        Translation NEW = from("new");
        Translation NEW_TOOLTIP = from("new.info");
        Translation ALERT = from("alert");
        Translation CLIENT = from("client");
        Translation CLIENT_TOOLTIP = from("client.info");
        Translation SERVER = from("server");
        Translation SERVER_TOOLTIP = from("server.info");
        Translation DYNAMIC = from("dynamic");
        Translation DYNAMIC_TOOLTIP = from("dynamic.info");
        Translation RELOAD = from("reload");
        Translation RELOAD_TOOLTIP = from("reload.info");
        Translation WARNING = from("warning");
        Translation SYNC = from("sync");
        Translation SYNC_TOOLTIP = from("sync.info");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.tag." + name);
        }
    }

    interface Text
    {
        Translation TOGGLE = from("toggle");
        Translation WAIT = from("wait");
        Translation WARN = from("warn");
        Translation FAIL = from("fail");
        Translation LOADED = from("loaded");
        Translation MOD_CONFLICT = from("mod_conflict");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.text." + name);
        }
    }

    /* Overlays */

    /**
     * Lang keys that are used by default overlay screens.
     */
    interface Overlay
    {
        Translation DRAG_TIP = from("drag");

        @SuppressWarnings("SameParameterValue")
        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.overlay." + name);
        }
    }

    /**
     * Lang keys that are used by error overlay screens.
     */
    interface Error
    {
        Translation IO_TITLE = from("io_title");
        Translation SERVER_TITLE = from("server_title");
        Translation JAVA_TITLE = from("java.title");
        Translation JAVA_MESSAGE = from("java.message");
        Translation APPLY_TITLE = from("apply_backup.title");
        Translation APPLY_MESSAGE = from("apply_backup.message");
        Translation IMPORT_TITLE = from("import.title");
        Translation IMPORT_MESSAGE = from("import.message");
        Translation CREATE_BACKUP_TITLE = from("create_backup.title");
        Translation CREATE_BACKUP_MESSAGE = from("create_backup.message");
        Translation VIEW_CLIENT_BACKUPS = from("view_client_backups");
        Translation DELETE_CLIENT_BACKUP = from("delete_client_backup");
        Translation DELETE_SERVER_BACKUP = from("delete_server_backup");
        Translation DELETE_ALL_BACKUPS = from("delete_all_backups");
        Translation INSPECT_BACKUP = from("inspect_backup");
        Translation BACKUP_NONEXISTENT = from("backup_nonexistent");
        Translation SERVER_APPLY = from("server_apply");
        Translation SERVER_BACKUPS = from("server_backups");
        Translation DOWNLOAD_WRITER = from("download_writer");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.overlay.error." + name);
        }
    }

    /**
     * Lang keys that are used by informative overlay screens.
     */
    interface Info
    {
        Translation IMPORT_CLIENT_TITLE = from("import_client.title");
        Translation IMPORT_CLIENT_MESSAGE = from("import_client.message");
        Translation IMPORT_SERVER_TITLE = from("import_server.title");
        Translation IMPORT_SERVER_MESSAGE = from("import_server.message");
        Translation EXPORT_CLIENT_TITLE = from("export_client.title");
        Translation EXPORT_CLIENT_MESSAGE = from("export_client.message");
        Translation EXPORT_SERVER_TITLE = from("export_server.title");
        Translation EXPORT_SERVER_MESSAGE = from("export_server.message");
        Translation CREATE_BACKUP_TITLE = from("create_backup.title");
        Translation CREATE_BACKUP_MESSAGE = from("create_backup.message");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.overlay.info." + name);
        }
    }

    /**
     * Lang keys that are used by the color overlay screen.
     */
    interface Picker
    {
        Translation TITLE = from("title");
        Translation OPEN = from("open");
        Translation SAMPLE = from("sample");
        Translation OPACITY = from("opacity");
        Translation PALETTE = from("palette");
        Translation HINT = from("hint");
        Translation RANDOM = from("random");
        Translation RECENT = from("recent");
        Translation RANDOMIZE = from("randomize");
        Translation CLEAR = from("clear");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.overlay.picker." + name);
        }
    }

    /**
     * Lang keys that are used by the status overlay screen.
     */
    interface Status
    {
        Translation TITLE = from("title");
        Translation LOADED = from("loaded");
        Translation WAIT = from("wait");
        Translation WARN = from("warn");
        Translation FAIL = from("fail");
        Translation CONFLICT = from("conflict");
        Translation CLIENT_ONLY = from("client_only");
        Translation NO_CONNECTION = from("no_connection");
        Translation DYNAMIC = from("dynamic");
        Translation DYNAMIC_OPERATOR = from("dynamic_operator");
        Translation DYNAMIC_VERIFIED = from("dynamic_verified");
        Translation DYNAMIC_UNVERIFIED = from("dynamic_unverified");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.overlay.status." + name);
        }
    }

    /**
     * Lang keys that are used by the config management overlay screen.
     */
    interface Manage
    {
        Translation TITLE = from("title");
        Translation HOT_SWAP = from("hot_swap");
        Translation HOT_SWAP_INFO = from("hot_swap.info");
        Translation IMPORT_ADVISORY_TITLE = from("import_advisory.title");
        Translation IMPORT_ADVISORY_MESSAGE = from("import_advisory.message");
        Translation IMPORT_EXPORT_HEADER = from("import_export.header");
        Translation CLIENT_IMPORT = from("import_export.client_import");
        Translation CLIENT_EXPORT = from("import_export.client_export");
        Translation SERVER_IMPORT = from("import_export.server_import");
        Translation SERVER_EXPORT = from("import_export.server_export");
        Translation CREATE_BACKUP_HELP = from("create_backup.help");
        Translation CREATE_BACKUP_VIEW = from("create_backup.view");
        Translation CREATE_BACKUP_CLIENT = from("create_backup.client");
        Translation CREATE_BACKUP_SERVER = from("create_backup.server");
        Translation VIEW_BACKUPS_WAITING = from("view_backups.waiting");
        Translation VIEW_BACKUPS_DISCONNECTED = from("view_backups.disconnected");
        Translation VIEW_BACKUPS_LAST_MODIFIED = from("view_backups.last_modified");
        Translation VIEW_BACKUPS_FILENAME = from("view_backups.filename");
        Translation VIEW_BACKUPS_EMPTY = from("view_backups.empty");
        Translation OPERATIONS_WIP = from("operations.wip");
        Translation OPERATIONS_WIP_MESSAGE = from("operations.wip.message");
        Translation OPERATIONS_SSO = from("operations.sso");
        Translation OPERATIONS_SSO_MESSAGE = from("operations.sso.message");
        Translation OPERATIONS_LOGGING = from("operations.logging");
        Translation OPERATIONS_LOGGING_MESSAGE = from("operations.logging.message");
        Translation OPERATIONS_DEBUG = from("operations.debug");
        Translation OPERATIONS_DEBUG_MESSAGE = from("operations.debug.message");
        Translation TOGGLE_ALL_HEADER = from("toggle_all.header");
        Translation TOGGLE_ALL_ENABLE = from("toggle_all.enable");
        Translation TOGGLE_ALL_DISABLE = from("toggle_all.disable");
        Translation TOGGLE_ALL_OVERRIDE = from("toggle_all.override");
        Translation TOGGLE_ALL_LOCAL = from("toggle_all.local");
        Translation TOGGLE_ALL_NETWORK = from("toggle_all.network");
        Translation TOGGLE_ALL_LOCAL_INFO = from("toggle_all.local.info");
        Translation TOGGLE_ALL_NETWORK_INFO = from("toggle_all.network.info");
        Translation TOGGLE_ALL_ENABLE_INFO = from("toggle_all.enable.info");
        Translation TOGGLE_ALL_DISABLE_INFO = from("toggle_all.disable.info");
        Translation TOGGLE_ALL_OVERRIDE_INFO = from("toggle_all.override.info");
        Translation TOGGLE_ALL_APPLY_INFO = from("toggle_all.apply.info");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.overlay.manage." + name);
        }
    }

    /**
     * Lang keys that are used by the help section in the config management overlay screen.
     */
    interface Help
    {
        Translation HEADER = from("header");
        Translation TWEAK_TAGS_TITLE = from("tweak_tags.title");
        Translation TWEAK_TAGS_MESSAGE = from("tweak_tags.message");
        Translation SEARCH_TAGS_TITLE = from("search_tags.title");
        Translation SEARCH_TAGS_MESSAGE = from("search_tags.message");
        Translation SHORTCUT_TITLE = from("shortcut.title");
        Translation SHORTCUT_MESSAGE = from("shortcut.message");
        Translation SHORTCUT_SEARCH = from("shortcut.search");
        Translation SHORTCUT_SAVE = from("shortcut.save");
        Translation SHORTCUT_EXIT = from("shortcut.exit");
        Translation SHORTCUT_JUMP = from("shortcut.jump");
        Translation SHORTCUT_ALL = from("shortcut.all");
        Translation SHORTCUT_CATEGORY = from("shortcut.category");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.overlay.manage.help." + name);
        }
    }

    /* Tweak Messages */

    /**
     * Lang keys that are used by tweak alert messages.
     */
    interface Alert
    {
        Translation NONE = from("none");
        Translation VOID = from("void");
        Translation LIGHT = from("light");
        Translation SHIELD = from("shield");
        Translation BRIGHTNESS = from("brightness");
        Translation DYNAMIC_FOG = from("dynamic_fog");
        Translation DYNAMIC_SKY = from("dynamic_sky");
        Translation UNIVERSAL_FOG = from("universal_fog");
        Translation UNIVERSAL_SKY = from("universal_sky");
        Translation FOOD_STACKING = from("food_stacking");
        Translation FOOD_HEALTH = from("food_health");
        Translation WINDOW_TITLE_DISABLED = from("window_title_disabled");
        Translation ROW_HIGHLIGHT_DISABLED = from("row_highlight_disabled");
        Translation CUSTOM_GUI_GRADIENT = from("custom_gui_gradient");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.alert." + name);
        }
    }

    /**
     * Lang keys that are used by tweak issue messages.
     */
    interface Issue
    {
        Translation SODIUM = from("sodium");
        Translation OPTIFINE = from("optifine");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.issue." + name);
        }
    }

    /* Enumerations */

    /**
     * Lang keys that are used by tweak enumerations.
     */
    interface Enum
    {
        Translation CLASSIC = from("classic");
        Translation ALPHA = from("alpha");
        Translation BETA = from("beta");
        Translation MODERN = from("modern");
        Translation DISABLED = from("disabled");
        Translation SCREEN_HOME = from("screen.home");
        Translation SCREEN_PACKS = from("screen.packs");
        Translation SCREEN_CONFIG = from("screen.config");
        Translation BACKGROUND_SOLID_BLACK = from("background.solid_black");
        Translation BACKGROUND_SOLID_BLUE = from("background.solid_blue");
        Translation BACKGROUND_GRADIENT_BLUE = from("background.gradient_blue");
        Translation RECIPE_BOOK_DISABLED = from("recipe_book.disabled");
        Translation RECIPE_BOOK_LARGE = from("recipe_book.large");
        Translation RECIPE_BOOK_SMALL = from("recipe_book.small");
        Translation DEBUG_CHART_DISABLED = from("debug_chart.disabled");
        Translation DEBUG_CHART_CLASSIC = from("debug_chart.classic");
        Translation DEBUG_CHART_MODERN = from("debug_chart.modern");
        Translation INVENTORY_SHIELD_INVISIBLE = from("inventory.shield.invisible");
        Translation INVENTORY_SHIELD_MIDDLE_RIGHT = from("inventory.shield.middle_right");
        Translation INVENTORY_SHIELD_BOTTOM_LEFT = from("inventory.shield.bottom_left");
        Translation ALPHA_BETA = from("basic.alpha_beta");
        Translation BASIC_CLASSIC = from("basic.classic");
        Translation BASIC_INF_DEV = from("basic.inf_dev");
        Translation CORNER_TOP_LEFT = from("corner.top_left");
        Translation CORNER_TOP_RIGHT = from("corner.top_right");
        Translation CORNER_BOTTOM_LEFT = from("corner.bottom_left");
        Translation CORNER_BOTTOM_RIGHT = from("corner.bottom_right");
        Translation FOG_ALPHA_R164 = from("fog.alpha_r164");
        Translation FOG_R17_R118 = from("fog.r17_r118");

        private static Translation from(String name)
        {
            return new Translation("gui.nostalgic_tweaks.enum." + name);
        }
    }

    /* Language Keys */

    /**
     * Functional helper that provides a component supplier that changes based on the given boolean supplier.
     *
     * @param supplier A {@link Supplier} that yields a {@code boolean}.
     * @param ifTrue   A {@link Translation} to get a {@link Component} from when the given supplier is {@code true}.
     * @param ifFalse  A {@link Translation} to get a {@link Component} from when the given supplier is {@code false}.
     * @return A {@link Supplier} that yields a {@link Component} based on the given arguments.
     */
    static Supplier<Component> supply(Supplier<Boolean> supplier, Translation ifTrue, Translation ifFalse)
    {
        return () -> supplier.get() ? ifTrue.get() : ifFalse.get();
    }
}