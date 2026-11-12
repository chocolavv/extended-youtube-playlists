# Extended YouTube Playlists

Patches for YouTube, for use with [Morphe](https://morphe.software).

## ❓ About

YouTube builds playlists for every channel: all uploads, only videos, only
Shorts, only live streams, the popular ones, the members-only ones. They work,
but the app never lists them. This patch puts them on top of the channel's
**Playlists** tab:

| | All kinds | Videos | Shorts | Live streams |
|---|:---:|:---:|:---:|:---:|
| **All** | ✓ | ✓ | ✓ | ✓ |
| **Popular** | ✓ | ✓ | ✓ | ✓ |
| **Members only** | ✓ | ✓ | ✓ | ✓ |

<img src=".github/assets/screenshot.png" alt="Hidden playlists on top of the Playlists tab of two channels" width="720">

- The cards are the app's own cards: titles come from YouTube in your
  language, with real thumbnails and video counts.
- Empty lists are not shown. A list that is a copy of another one is not shown.
- The tab appears even on a channel that has no public playlists.
- The hidden lists stay first under both sort orders.

### How to use these patches

Click the button below, or add this repo url to Morphe Manager by hand:
https://github.com/chocolavv/extended-youtube-playlists

<a href="https://morphe.software/add-source?github=chocolavv/extended-youtube-playlists">
  <img src=".github/assets/add-to-morphe.svg" alt="Add to Morphe" height="48">
</a>

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->
The list is filled in by the first release.
<!-- PATCHES_END -->

## 🛠️ Building locally

- Run `./gradlew buildAndroid`.
- The patches file is `patches/build/libs/patches-*.mpp`.
- Apply it with [Morphe Desktop](https://github.com/MorpheApp/morphe-desktop)
  like any other patches file.

## 📜 License

Extended YouTube Playlists is licensed under the
[GNU General Public License v3.0](LICENSE). Not made by or linked to the
Morphe project or YouTube.
