package app.extendedplaylists.patches.youtube

import app.morphe.patcher.extensions.InstructionExtensions.addInstructionsWithLabels
import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility
import app.morphe.patcher.patch.patch
import app.morphe.patcher.util.findFreeRegister
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

private const val EXTENSION = "Lapp/extendedplaylists/extension/HiddenPlaylists;"

internal val COMPATIBILITY_YOUTUBE = Compatibility(
    name = "YouTube",
    packageName = "com.google.android.youtube",
    apkFileType = ApkFileType.APK_REQUIRED,
    appIconColor = 0xFF0033,
    targets = listOf(AppTarget(version = "21.30.216", minSdk = 29)),
)

@Suppress("unused")
val hiddenPlaylistsPatch = patch(
    name = "Hidden playlists",
    description = "Shows the playlists YouTube builds for every channel in the Playlists tab of the channel.",
    default = true,
) {
    compatibleWith(COMPATIBILITY_YOUTUBE)
    extendWith("extensions/extension.mpe")

    execute {
        // The browse response class: the type of the message field the Parcelable writes.
        val browse = BrowseResponsePayloadFingerprint.instructionMatches.first().fieldReference!!.type

        // Right after the parse, a browse response is replaced by what the extension returns for it.
        ResponseParserFingerprint.method.apply {
            val index = ResponseParserFingerprint.instructionMatches.last().index
            val message = getInstruction<OneRegisterInstruction>(index).registerA
            val free = findFreeRegister(index + 1, message)
            addInstructionsWithLabels(
                index + 1,
                """
                    instance-of v$free, v$message, $browse
                    if-eqz v$free, :other
                    invoke-static/range { v$message .. v$message }, $EXTENSION->onBrowseResponse(Ljava/lang/Object;)Ljava/lang/Object;
                    move-result-object v$message
                    :other
                    nop
                """
            )
        }
    }
}
