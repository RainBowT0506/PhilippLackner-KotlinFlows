Github：[PhilippLackner-KotlinFlows](https://github.com/RainBowT0506/PhilippLackner-KotlinFlows)
# [Flow Basics](https://www.youtube.com/watch?v=ZX8VsqNO_Ss)
## 引言與播放清單介紹
- 計劃製作一個深入探討 Kotlin Flow 的播放清單。
- 之前已有一些關於 StateFlow、SharedFlow 和 Flow 的影片，但缺少一個全面深入的系列。
- Flow 在 Android 開發中非常重要，幾乎每天都會用到，因此希望提供一個從頭到尾的完整學習資源。
- 這個播放清單將分為三個影片：
  - 第一個影片介紹 Flow 的基礎及使用方式。
  - 第二個影片將深入探討 Flow Operators。
  - 第三個影片將詳細講解 StateFlow 和 SharedFlow。

## Flow 基本概念介紹
![CleanShot 2024-08-14 at 13.58.23](https://hackmd.io/_uploads/r16YwTKcR.png)
 
- Flow 是 Kotlin 中一種能夠在一段時間內發射多個值的協程。
- 傳統的協程或延遲函數只能返回單一值，而 Flow 能解決這個問題，適用於像倒數計時器這類需要持續發射值的情境。
- Flow 是反應式編程的一部分，允許我們隨時處理數據的變化。
- Flow 可以類比為組裝線，依次處理數據並最終輸出。

## 簡單倒數計時器範例
- 創建一個 ViewModel 並定義倒數計時器的 Flow。
- 使用 Flow Builder 創建一個能夠每秒減少倒數計時的 Flow。
- 在 MainActivity 中顯示倒數計時的結果，並介紹如何在 Jetpack Compose 中使用 Flow 的狀態。

## 如何收集 Flow 的數據
- 使用 `collect` 函數收集 Flow 的數據，每當 Flow 發射新值時，`collect` 函數中的代碼塊就會被觸發。
- `collectLatest` 的用法：與 `collect` 的區別在於，當新值進來時，`collectLatest` 會取消之前尚未完成的數據處理，只處理最新的數據。

## 熱流與冷流 (Hot Flow vs. Cold Flow)
- Flow 是一種冷流 (Cold Flow)，只有當有收集者時，才會開始發射數據。
- 熱流 (Hot Flow) 則會在無論是否有收集者的情況下，持續發射數據。

# [Flow Operators](https://www.youtube.com/watch?v=sk3svS_fzZM)
## 引言與本影片介紹
- 這是 Kotlin Flow 播放清單的第二部分，將深入探討 Flow Operators。
- 將從簡單開始，逐步增加複雜性，到影片結尾將介紹較為複雜的概念。
- Flow Operators 是用來決定 Flow 中的每次發射值會發生什麼事。

## Flow Operators 基本介紹
- Flow Operators 是用來處理和轉換 Flow 中的發射值。
- 在上個影片中，我們使用了簡單的倒數計時器來演示 Flow 的基本用法。
- Flow 不僅能收集數據，還能應用各種操作符來轉換數據。

## 範例操作符：Filter
- Filter 操作符可以過濾 Flow 的發射值，只保留符合條件的值。
- 例如，可以只保留偶數的發射值，其他的則丟棄。

## 範例操作符：Map
- Map 操作符可以將發射值轉換為新的值。
- 例如，可以將發射值平方後再傳遞給下一步處理。

## 範例操作符：OnEach
- OnEach 操作符用來對每個發射值進行操作，但不會改變它們。
- 可以用來記錄或打印發射值，也可以與其他操作符鏈結使用。

## 使用 Terminal Flow Operators
- Terminal Flow Operators 會終結 Flow，將所有發射值進行最終處理。
- 範例操作符：Count
  - 計算符合條件的發射值數量。
  - 例如，計算所有偶數發射值的數量。

## 範例操作符：Reduce
- Reduce 操作符將所有發射值累積處理，最終返回一個值。
- 例如，累加所有發射值，得到它們的總和。

## 範例操作符：Fold
- Fold 與 Reduce 相似，但 Fold 可以提供一個初始值作為累積的起點。
- 例如，從 100 開始累加發射值，最終得到總和。

## Flattening 基本概念
- 在 Flow 中，Flattening 不僅適用於列表，還適用於 Flow 本身，允許將多個 Flow 合併為一個單一的 Flow。
- 常見的 Flattening 操作符包括 `flatMapConcat`、`flatMapMerge` 和 `flatMapLatest`。

## 範例操作符：flatMapConcat
- `flatMapConcat` 會逐一處理每個 Flow 的發射值，按順序處理完一個 Flow 後才開始處理下一個。
- 例如，將兩個 Flow 的結果依次發射到單一 Flow 中。

## 範例操作符：flatMapMerge
- `flatMapMerge` 與 `flatMapConcat` 的區別在於，它不等待前一個 Flow 完成，而是同時處理多個 Flow 的發射值。
- 適合用於不需要依賴順序的情況，但在一般應用中較少使用。

## 範例操作符：flatMapLatest
- `flatMapLatest` 像 `collectLatest` 一樣，只保留最新的發射值，如果有新的發射值出現，則取消前一個處理並開始處理新的值。
- 適合處理頻繁更新的狀態，確保只關注最新的結果。

## Buffer 與 Conflate 操作符
- `buffer` 會將 Flow 的發射和收集分離到不同的協程中，允許 Flow 繼續發射，即使收集器尚未處理完之前的值。
- `conflate` 會丟棄舊的未處理值，只保留最新的發射值，適合用於只關注最新狀態的場景。
- `collectLatest` 則直接忽略先前未完成的收集，立刻開始處理最新的發射值。

# [StateFlow & SharedFlow](https://www.youtube.com/watch?v=za-EEkqJLCQ)
## 介紹

- 這是這個系列課程的第三部分，將討論 `StateFlow` 和 `SharedFlow`，並比較它們與 `LiveData` 的不同。
- 影片中會展示如何使用這些工具，以及在實踐中何時使用它們。

## StateFlow 簡介

- `StateFlow` 是用來保存狀態的，類似於 `LiveData`，但沒有生命週期感知能力。
- `StateFlow` 會通知所有的收集器（`collector`）當有新變化時，並只保存一個單一值。
- 它屬於「熱流」（Hot Flow），即使沒有收集器，仍會執行動作。

## StateFlow 的使用

- `StateFlow` 通常用於 `ViewModel` 中保存 UI 狀態。
- 一般會在 `ViewModel` 中宣告一個私有的可變版本（`MutableStateFlow`），僅供 `ViewModel` 修改。
- 公有的不變版本則供 UI 訂閱和收集。

## 宣告 StateFlow

- 私有的可變版本通常命名為 `_stateFlow`，並以 `MutableStateFlow` 宣告，需提供初始值。
- 公有的不變版本使用 `asStateFlow` 函數從可變版本中獲取。

## 實作範例：計數器

- 建立了一個簡單的計數器範例，用於展示 `StateFlow` 的功能。
- 當計數器增加時，`StateFlow` 的值會更新並通知 UI。
- `StateFlow` 的值在裝置旋轉後仍會保持，因此 UI 會顯示正確的狀態。

## 在 Compose 中使用 StateFlow

- 在 Compose 中，`StateFlow` 可以輕鬆與 UI 綁定，但在 Compose 中建議使用 Compose 的 `State`，而非 `StateFlow`。
- 若從其他庫獲得 `StateFlow`，可以使用 `collectAsState` 進行收集。

## 在 XML 中使用 StateFlow

- 在 XML 中觀察和收集 `StateFlow` 需要注意使用 `repeatOnLifecycle` 函數來處理生命週期。
- 傳統使用 `lifecycleScope.launch` 並不足夠，需要搭配 `repeatOnLifecycle` 才能確保在 UI 的適當時機收集 `StateFlow`。

## 建立方便的擴展函數

- 創建了一個方便的擴展函數 `collectLatestLifecycleFlow` 來簡化 `StateFlow` 的收集。
- 這個函數可以減少代碼的縮排和重複，使代碼更簡潔。
- 使用這個函數後，只需要一個簡單的呼叫即可處理 `StateFlow` 的收集和 UI 更新。

## 比較 StateFlow 與 LiveData

- `StateFlow` 擁有更多的操作符，可以進行如 `map` 和 `flatMap` 等操作，比 `LiveData` 更加靈活。
- 使用 `StateFlow` 可以更好地利用協程，這在現代 Android 開發中是更推薦的方式。

## 介紹 SharedFlow

- 這部分將討論 `SharedFlow`，並比較它與 `StateFlow` 和一般的 `Flow` 的不同之處。
- `SharedFlow` 主要用來傳送一次性事件，例如顯示 Snackbar 或導航到另一個畫面。

## SharedFlow 與 StateFlow 的比較

- `StateFlow` 是用來保存狀態的，例如螢幕旋轉後仍需保持的狀態。
- `SharedFlow` 用來處理一次性事件，這些事件在螢幕旋轉後不應該再次觸發，例如導航事件。

## SharedFlow 的用途

- `SharedFlow` 可用於傳送例如顯示 Snackbar 或觸發導航的事件。
- 若事件需要被多個觀察者（`collector`）接收，使用 `SharedFlow` 是合適的選擇。

## SharedFlow 的特性

- 與 `StateFlow` 一樣，`SharedFlow` 也是「熱流」（Hot Flow），即使沒有收集器，事件也不會被保留。
- 若在事件發送時沒有收集器，事件將會丟失，不會被保留在 `SharedFlow` 中。

## 宣告 SharedFlow

- 在 `ViewModel` 中宣告 `SharedFlow` 與 `StateFlow` 相似，不需要初始值。
- 可以使用 `emit` 函數從任何位置發送事件。

## SharedFlow 的行為

- 當事件發送時，所有的收集器都會接收到該事件，且 `emit` 函數會暫停，直到所有收集器處理完成為止。
- 需要注意，如果事件在收集器初始化之前發送，事件將會丟失。

## 設置 Replay Cache

- 可以設置 `SharedFlow` 的 `replay` 參數來緩存事件，允許新加入的收集器接收到已發送的事件。
- `replay` 參數設置為 5，表示最多可以緩存 5 個事件供新收集器接收。

## 使用 SharedFlow 的範例

- 宣告一個 `SharedFlow`，並發送一個事件後等待收集器處理該事件。
- 設置 `replay` 參數，使事件在發送後，即使沒有收集器，也能被後續的收集器接收到。

## 在 Jetpack Compose 中使用 SharedFlow

- 在 Jetpack Compose 中使用 `SharedFlow` 需要透過 `launchEffect`，這樣可以確保該區塊只會被啟動一次。
- 使用 `collect` 函數來收集 `SharedFlow` 中的事件並更新 UI。

## 使用 SharedFlow 的注意事項

- 當在 XML 中使用 `SharedFlow` 時，避免使用 `collectLatest`，以確保所有事件都被正確處理。
- 可建立另一個版本的函數來處理 `SharedFlow` 的收集，確保事件不會被遺失。

# [Unit Testing Flows](https://www.youtube.com/watch?v=rk6aKkWqqcI)
## 介紹

- 這是 Kotlin Flows 教程的第四部分，主要介紹如何對 Flows 進行單元測試。
- 在大多數專案中，處理 Flows 是不可避免的，因此學習如何正確地進行單元測試非常重要。

## 準備工作
```
 testImplementation("app.cash.turbine:turbine:0.7.0")
 testImplementation("com.google.truth:truth:1.1.3")
 testImplementation("org.jetbrains.kotlin:kotlinx-coroutines-test:1.5.1")
```
- 需要包含一些額外的依賴庫來進行單元測試：
  - **Turbine**：使得測試 Flows 更加簡單。
  - **Truth**：Google 的一個庫，提供更好的測試斷言功能。
  - **Coroutine Test**：提供一些協程測試功能，用於正確地測試協程。

## 測試範例介紹

- 測試將針對一個倒數計時的 Flow，該 Flow 從 5 開始倒數，每秒減少一個值。
- 這類測試並不容易，因為涉及延遲和不同時間點的多次發射。

## 建立測試文件
![CleanShot 2024-08-15 at 21.54.35](https://hackmd.io/_uploads/HJmndFiqR.png)

- 創建一個測試文件 `MainViewModelTest`，確保選擇 `JUnit 4` 並生成一個 `setup` 函數。
- 在 `setup` 中，每次測試前初始化 `ViewModel`，確保每個測試都是從乾淨的狀態開始。

## 使用 Turbine 測試 Flow

- `Turbine` 提供了一個 `test` 函數，可以直接用於測試 Flow。
- 在測試中使用 `awaitItem` 函數來等待 Flow 的發射，並將發射的值與期望值進行比較。
- 使用 `runBlocking` 來包裹測試，確保協程在測試結束前運行完畢。

## 解決延遲問題
```
Exception in thread "Test worker" java.lang.IllegalStateException: Module with the Main dispatcher had failed to initialize. For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used
```
- 測試倒數計時的 Flow 時，每次執行測試都需要等待實際的延遲時間，這會使測試變得非常耗時。
- 解決方法是注入 `Coroutine Dispatcher`，這樣可以在測試中跳過延遲。

## 注入 Coroutine Dispatcher

- 創建一個 `DispatcherProvider` 介面，定義應用中需要的不同 `Dispatcher`。
- 創建一個具體的實現 `DefaultDispatchers`，在實際的 Android 專案中使用這個實現。

## DispatcherProvider 的實現

- `DispatcherProvider` 介面包含主線程 (`Main`)、I/O 操作 (`IO`) 和默認 (`Default`) 的 Dispatcher。
- 在 `DefaultDispatchers` 類中為這些 Dispatcher 指定對應的 Coroutine Dispatcher。

## 測試中的應用

- 在測試中，可以使用 `TestCoroutineDispatcher` 來代替默認的 Dispatcher，這樣可以跳過延遲。
- 測試時，使用這個自訂的 Dispatcher，保證測試快速且不依賴實際的時間流逝。 

## 將 Dispatcher 注入 ViewModel

- 將 `DispatcherProvider` 介面注入到 ViewModel 中。
- 所有的協程必須使用這些注入的 Dispatcher，確保能在測試中使用自訂的 Dispatcher。
- 使用 `flowOn(dispatcher.main)` 來設置協程在主線程上運行。

## 測試使用 Test Dispatcher

- 在測試中，使用 `TestCoroutineDispatcher` 替代默認的 Dispatcher。
- 在測試類中創建一個 `TestDispatchers` 類來實現 `DispatcherProvider`，並使用 `TestCoroutineDispatcher`。

## 測試倒數計時 Flow

- 使用 `advanceTimeBy` 函數來跳過延遲，確保測試快速進行。
- 使用 `cancelAndConsumeRemainingEvents` 確保測試完成時處理所有剩餘事件。

## 測試 Square Number Function

- 測試 `SharedFlow` 時，需確保事件在收集器啟動後觸發。
- 使用 `launch` 創建一個協程，確保事件發送與收集器同步運行。
- 使用 `job.join()` 確保協程完成後再檢查結果。

## 測試提示與最佳實踐
```
Timed out waiting for 1000 ms
kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1000 ms
```
- 測試時將事件發送與收集器分離，以避免未處理事件的問題。
- 測試結束時使用 `cancelAndConsumeRemainingEvents` 確保所有事件都被正確消耗。
- 建議注入 Dispatcher 作為測試中的最佳實踐，以便更靈活地控制協程行為。


# Clean Flow Transformations with Combine, Zip & Merge
## combine 運算符
![CleanShot 2024-08-15 at 22.56.18](https://hackmd.io/_uploads/rylmDcjqR.png)

- 用於合併多個 Flow 的值，當任一 Flow 有新值時就會觸發。
- 適合同時監聽多個數據來源，並在其中任何一個發生變化時更新狀態。
- 例子：將用戶資料和用戶帖子資料合併，並在任何一個發生變化時更新用戶的個人資料狀態。

## 多個 Flow 的 combine
![CleanShot 2024-08-15 at 22.58.24](https://hackmd.io/_uploads/BJR5vqjcC.png)

- 可以先合併部分 Flow，再與其他 Flow 進行合併，以處理更複雜的邏輯。
- 合併用戶認證狀態、用戶資料和帖子資料，並根據認證狀態決定是否更新個人資料。
- 使用 combine 可以減少多個收集器的重複操作。

## zip 運算符
![CleanShot 2024-08-15 at 22.59.45](https://hackmd.io/_uploads/Hyeld9o9C.png)
![CleanShot 2024-08-15 at 23.01.33](https://hackmd.io/_uploads/BJt8Oco9R.png)
![CleanShot 2024-08-15 at 23.01.58](https://hackmd.io/_uploads/B1Xudco9R.png)


- 用於將多個 Flow 的值配對，當每個 Flow 都有新值時才會觸發。
- 與 combine 的不同在於 zip 會等待所有 Flow 都有新值時才進行配對。
- 適合需要同時配對多個 Flow 值的情況，例如將兩個不同速度發送數字的 Flow 進行配對。

## merge 運算符
![CleanShot 2024-08-15 at 23.02.40](https://hackmd.io/_uploads/rJ1s_cj9R.png)
![CleanShot 2024-08-15 at 23.02.54](https://hackmd.io/_uploads/Bkk2_cs9R.png)

- 用於將多個 Flow 合併成一個 Flow，所有 Flow 的值會依次被發送。
- 不會等待其他 Flow，立即發送任一 Flow 的值。
- 適合將多個 Flow 的發送值合併成一個連續的 Flow，以便更簡單地進行處理。

# Terminology
- **Kotlin Flow**：一種Kotlin的協程框架，允許在一段時間內發射多個值，適合處理異步數據流。
- **StateFlow**：一種特殊的Flow，用於表示和跟蹤應用狀態的變更。
- **SharedFlow**：另一種特殊的Flow，允許多個收集器共享同一數據流。
- **Coroutines**：Kotlin中的一種輕量級並發工具，用於執行異步操作。
- **Suspend Function**：Kotlin中的一種函數，可以暫停執行並稍後恢復，通常與協程一起使用。
- **Reactive Programming**：一種編程範式，關注於數據流的變更，並在數據變化時觸發相應的反應。
- **Flow Builder**：用於創建Flow的一個Lambda表達式，可以在其中定義如何生成和發射數據。
- **Flow Collector**：用於收集和處理Flow發射的數據，通常用於響應Flow的變化。
- **Emit**：Flow中用於發射新數據的函數，每當Flow中有新數據產生時會觸發。
- **Collect**：用於收集Flow發射的數據，每當有新數據時，Collect的代碼塊會被執行。
- **Collect Latest**：類似於Collect，但會在新的數據到來時取消當前處理的數據，只保留最新的數據。
- **Cold Flow**：一種僅在有收集者時才開始發射數據的Flow。
- **Hot Flow**：即使沒有收集者，也會持續發射數據的Flow。
- **Jetpack Compose**：Kotlin的一種UI框架，用於構建Android界面，與Flow結合使用時可以實現響應式UI更新。
- **ViewModel**：Android架構組件中的一部分，用於管理UI相關的數據和邏輯，並在配置更改時保存數據。
- **ViewModelScope**：ViewModel中的一個協程範圍，用於執行需要與ViewModel的生命週期相關聯的協程操作。
- **Flow Operators**：Kotlin Flow中的一系列函數，用於控制Flow發射數據的處理方式，如過濾、轉換或終止流。
- **Filter**：Flow操作符，用於過濾Flow中的發射數據，只有符合條件的數據才會被傳遞給收集器。
- **Map**：Flow操作符，用於將Flow中的每個發射數據轉換為另一個值，類似於對列表中的每個元素進行映射。
- **OnEach**：Flow操作符，不會改變Flow中的數據，而是在每個數據發射時執行某些動作，如打印數據，並允許繼續鏈接其他操作符。
- **Collect**：用於收集Flow中的數據，通常是終止Flow的操作符，當Flow中的每個數據發射時執行指定的動作。
- **Collect Latest**：類似於Collect，但在新數據到來時會取消當前正在處理的數據，僅處理最新數據，適合UI狀態的反應。
- **Terminal Flow Operators**：終止Flow的操作符，通常用於對Flow中的所有數據進行彙總或計算。
- **Count**：終止操作符，計算符合條件的Flow數據發射次數，返回數量。
- **Reduce**：終止操作符，將Flow中所有數據逐步累加或處理，返回最終累積結果。
- **Fold**：類似於Reduce，但可以設置初始值，並從該值開始進行累積或處理。
- **Launch In**：用於將Flow啟動並運行在指定的協程範圍內，如ViewModelScope。
- **Flattening**：將多個Flow合併為一個Flow，類似於將多層結構展平成單層結構。
- **FlatMap Concat**：按順序處理Flow中的每個發射數據，等待前一個Flow完成後再處理下一個Flow。
- **FlatMap Merge**：同時處理多個Flow，不等待前一個Flow完成，並行處理多個結果。
- **FlatMap Latest**：僅保留最新的發射數據，丟棄正在處理的數據以處理最新的數據。
- **Buffer**：允許Flow的發射與收集操作在不同的協程中並行運行，不等待收集完成便繼續發射新數據。
- **Conflate**：如果Flow在收集過程中發射了多個數據，僅保留最新的一個，丟棄之前的數據。
- **Collect Latest**：取消當前的收集操作，直接處理最新的發射數據，適合處理最新狀態的場景。
- 終端運算子：指在資料處理流程的最後一步，通常是將資料流的結果輸出或進行最終操作。
- 扁平化（Flattening）：將巢狀結構（如巢狀清單）展平成單一結構的過程。
- Flow：Kotlin中用於處理非同步資料流的類型，可以發射多個值並進行處理。
- 延遲（delay）：在非同步流程中插入等待時間，通常用於模擬耗時操作。
- FlatMap：一種操作符，用於將一個Flow的每個元素轉換成另一個Flow，並將這些Flow合併成單一Flow。
- FlatMapConcat：按順序串接多個Flow的操作符，在前一個Flow完成後再開始下一個Flow。
- FlatMapMerge：同時執行多個Flow的操作符，而不等待前一個Flow完成。
- FlatMapLatest：只保留最新Flow的結果，丟棄之前的Flow。
- 緩衝（Buffer）：使資料流的發射與收集在不同的協程中執行，避免因收集端的延遲而阻塞資料流。
- 合併（Conflate）：跳過收集端未來得及處理的資料，只保留最新的發射值。
- CollectLatest：只處理資料流的最新發射值，當新的發射值出現時，丟棄尚未完成處理的舊值。
- **StateFlow**：用於保持狀態的Flow，類似於LiveData，但不具備生命週期感知能力，適合用來儲存和管理應用中的UI狀態。即使沒有觀察者，它仍然會保持最新的值，因此屬於熱流（Hot Flow）。
- **SharedFlow**：一種可以發射和重播事件的Flow，允許多個觀察者同時訂閱。不同於StateFlow，它不儲存狀態值，而是處理事件流，適合用於事件傳遞，如用戶輸入或網絡響應。
- **LiveData**：Android中的一種可觀察數據持有類型，具備生命週期感知能力，可自動調整數據更新，以避免在UI不活躍時進行更新，從而防止內存洩漏。
- **冷流（Cold Flow）**：只有在被收集時才會啟動的Flow，如果沒有觀察者，它將不會進行任何操作，典型用於懶加載和資源節省。
- **熱流（Hot Flow）**：無論有無觀察者，Flow都會持續運作和發射數據，適合用於需要即時更新的狀態管理。
- **ViewModel**：Android架構組件的一部分，用於保存UI相關的數據，即使在設備旋轉等配置變更時，數據仍然能保持不變，避免UI重新創建後數據丟失。
- **MutableStateFlow**：StateFlow的可變版本，允許在ViewModel內部修改狀態值，而UI只能觀察到StateFlow的不可變版本。
- **value**：StateFlow中存儲的當前值，可以通過這個屬性來讀取或修改StateFlow中的數據，類似於LiveData中的`getValue`和`setValue`方法。
- **repeatOnLifecycle**：在Android的Kotlin協程中使用的一個功能，用於在特定的生命週期狀態下啟動和停止協程。這樣可以避免內存洩漏和資源浪費。當`repeatOnLifecycle`被調用時，它會在指定的生命週期狀態（如`Lifecycle.State.STARTED`）進入時啟動協程，並在生命週期狀態離開時自動取消協程。這使得在Activity或Fragment的特定生命週期中運行長時間操作變得更加安全和高效。
- **StateFlow**：一種用於保持狀態的熱流，適合在Kotlin中管理UI狀態，特別是在ViewModel中存儲並管理UI狀態，使得旋轉設備或其他配置變更時，狀態能夠保持。
- **SharedFlow**：一種可以多次發射和重播事件的熱流，允許多個觀察者訂閱，用於事件傳遞而非狀態管理。
- **LiveData**：Android中的可觀察數據持有類型，具備生命週期感知能力，適合在UI層使用，但缺少像Flow那樣的操作符。
- **LifecycleScope**：一個與生命週期綁定的協程作用域，允許在Android組件的生命週期內安全地啟動和停止協程。
- **repeatOnLifecycle**：用於在特定的生命週期狀態下啟動和停止協程的函數，確保協程在指定的生命週期狀態內安全運行，並在狀態變化時自動取消或重新啟動。
- **collectLatest**：Flow中的一種操作符，用於只處理最新的數據發射，如果新數據發射前一個發射尚未完成，則取消前一個發射並處理最新發射。
- **launchWhenStarted**：舊版本的簡便方法，用於在生命週期處於STARTED狀態時啟動協程，但現在建議使用repeatOnLifecycle來代替。
- **ComponentActivity**：一個基礎的Activity類別，為使用Jetpack組件和協程提供了必要的支持。
- **collectLatestLifecycleFlow**：一個自定義的擴展函數，用於簡化在Android組件中收集StateFlow的代碼，使其在生命週期內安全運行並處理最新數據。
- **SharedFlow**：用於發送一次性事件的熱流，適合處理如顯示Snackbar或導航等只需觸發一次的事件。不同於StateFlow，SharedFlow不會儲存狀態值，而是處理事件流。
- **Channels**：類似於SharedFlow，但主要用於單一觀察者情境下的事件發送。若有多個觀察者，則更適合使用SharedFlow。
- **emit**：在SharedFlow中發送事件的方法。當事件被發送時，如果沒有訂閱者，則事件會被丟棄，不會儲存在Flow中。
- **replay**：SharedFlow的參數，用於設定事件的緩存數量。設置`replay`後，SharedFlow會儲存指定數量的事件，以供新訂閱者接收這些緩存的事件。
- **launchWhenStarted**：以前的協程啟動方式，現在被建議使用`repeatOnLifecycle`來代替。
- **suspend function**：Kotlin中的一種函數類型，可以掛起當前協程並非同步地執行操作，如`emit`函數。
- **collect**：收集SharedFlow或其他Flow的事件，用於處理和響應這些事件。
- **replay cache**：SharedFlow中的一種緩存機制，允許新的觀察者在訂閱時接收之前緩存的事件。
- **launchEffect**：在Jetpack Compose中使用，用於在組合外部執行非同步操作，適合用於處理一次性事件，如SharedFlow的收集。
- **ViewModel**：用於儲存和管理UI相關數據的架構組件，能夠在設備旋轉等配置變更時保留數據。
- **Jetpack Compose**：一種基於Kotlin的現代化UI工具包，用於構建Android的聲明式界面，不同於XML佈局。
- **單元測試（Unit Testing）**：測試程式中最小單位的行為，以確保其功能符合預期。
- **Flow**：Kotlin中的資料流，可以發射多個值並處理非同步操作。
- **延遲（delay）**：在Flow中插入的等待時間，通常用來模擬耗時操作。
- **Turbine**：一個Kotlin庫，用於簡化Flow單元測試，提供方法來等待並驗證Flow的發射結果。
- **Truth**：Google提供的斷言庫，用於替代傳統JUnit斷言，提供更可讀和強大的測試斷言。
- **runBlocking**：一個阻塞式Kotlin協程運行器，允許在測試中以同步方式運行協程，確保協程完成後測試才結束。
- **DispatcherProvider**：用於在應用中注入協程調度器的抽象接口，使得在測試中可以輕鬆替換協程調度器。
- **TestCoroutineDispatcher**：專門用於測試的協程調度器，允許控制協程的時間進程，模擬即時結果並加快測試速度。
- **setup**：在每個測試案例之前運行的初始化方法，用於設置測試的初始狀態。
- **Main Dispatcher**：Kotlin協程的主線程調度器，通常用於UI相關的協程，在單元測試中通常會被替換以適應非UI環境。
- **協程（Coroutine）**：Kotlin中用於非同步和並發編程的輕量級線程，允許順序的非同步代碼。
- **emit**：在Flow中發射數據的方法，用於生成Flow中的新值。
- **collect**：從Flow中收集數據的方法，通常用於處理發射的值。
- **ViewModel**：Android架構中的組件，用於存儲和管理UI相關的數據，即使在設備旋轉等配置變更時，數據仍能保持。
- **Jetpack Compose**：基於Kotlin的現代化UI工具包，用於構建Android的聲明式界面。
- **SharedFlow**：一種可以多次發射和重播事件的熱流，適合處理如顯示Snackbar或導航等一次性事件。
- **StateFlow**：用於保持狀態的熱流，適合在ViewModel中管理UI狀態。
- **replay**：SharedFlow的參數，用於設定事件的緩存數量，允許新訂閱者接收之前緩存的事件。
- **冷流（Cold Flow）**：只有在被收集時才會啟動的Flow，如果沒有觀察者，它將不會進行任何操作。
- **熱流（Hot Flow）**：無論有無觀察者，Flow都會持續運作和發射數據，適合用於需要即時更新的狀態管理。
- **依賴注入（Dependency Injection）**：將依賴物件傳入某個類別的方法，而非在類別內部直接建立物件，用以提升測試的靈活性與可控性。
- **DispatcherProvider**：用於提供協程調度器的接口，允許在應用中注入不同的協程調度器，尤其在測試中替換調度器以控制協程行為。
- **TestCoroutineDispatcher**：專門用於測試的協程調度器，允許控制協程的時間進程，如跳過延遲或模擬即時結果。
- **Flow On**：Kotlin中用於更改Flow運行協程調度器的操作符，用於指定Flow應該在哪個調度器上執行。
- **Main Dispatcher**：Kotlin協程的主線程調度器，通常用於UI相關的協程調度，但在測試中會被替換以適應非UI環境。
- **ViewModelScope**：一個用於ViewModel中的協程範圍，確保協程在ViewModel清理時被取消。
- **Lateinit**：Kotlin中的延遲初始化屬性，用於宣告在初始化前不需要馬上賦值的屬性，通常用於依賴注入的場景。
- **Turbine Test**：Turbine庫中的一個方法，用於Flow單元測試，允許輕鬆等待和驗證Flow的發射項目。
- **TestCoroutineScope**：一個特殊的協程範圍，專門用於測試環境中，允許控制協程的啟動和運行時間。
- **awaitItem**：Turbine庫中的方法，用於等待Flow中的下一個發射項目，並將其返回以進行驗證。
- **advanceTimeBy**：TestCoroutineDispatcher的方法，用於在測試中快速跳過指定的時間，模擬時間流逝。
- **cancelAndConsumeRemainingEvents**：Turbine中的方法，用於取消測試並消耗剩餘的事件，以避免未消耗的事件引發錯誤。
- **runBlocking**：Kotlin協程中阻塞當前線程的函數，允許在單元測試中以同步方式運行協程。
- **launch**：Kotlin中的協程構建器，用於啟動新的協程，允許非同步運行代碼。
- **join**：協程中的方法，用於等待另一個協程完成，常用於測試中確保協程完成後再進行驗證。
- **SharedFlow**：一種可以多次發射和重播事件的熱流，適合處理如顯示Snackbar或導航等一次性事件。
- **assertThat**：Truth庫中的斷言方法，用於驗證測試結果是否符合預期。
- **runBlockingTest**：用於測試協程的運行器，允許控制協程的運行和時間流逝。
- **Job**：Kotlin協程中的工作單元，代表一個協程的生命周期，可用於控制和監控協程的執行狀態。
- **emit**：在Flow或SharedFlow中發射數據的方法，用於生成和發送新值。
- **flowOn**：Kotlin中用於更改Flow運行協程調度器的操作符，指定Flow在哪個調度器上執行。它允許將Flow的發射和收集操作分別在不同的協程調度器上執行，以實現更靈活的並發控制。通常用於在背景線程中處理Flow的數據流，然後在主線程中收集結果。
- **Flow**：Kotlin中的資料流，可以發射多個值並處理非同步操作。
- **StateFlow**：一種熱流，用於保持狀態，適合在ViewModel中管理UI狀態。
- **SharedFlow**：一種熱流，用於發送一次性事件，適合處理如顯示Snackbar或導航等事件。
- **MutableStateFlow**：StateFlow的可變版本，允許在ViewModel內部修改狀態值。
- **MutableSharedFlow**：SharedFlow的可變版本，允許發送新的事件。
- **collect**：從Flow中收集數據的方法，通常用於處理發射的值。
- **emit**：在Flow或SharedFlow中發射數據的方法，用於生成和發送新值。
- **combine**：Flow運算符，用於合併多個Flow的值，當任一Flow發生變化時觸發。
- **zip**：Flow運算符，用於將多個Flow的值配對組合，僅在所有Flow都有新的值時觸發。
- **merge**：Flow運算符，用於將多個Flow合併為一個Flow，按發射順序接收所有值。
- **FlowOn**：用於更改Flow運行協程調度器的操作符，指定Flow在哪個調度器上執行。
- **onEach**：用於對Flow的每次發射執行操作的運算符，類似於側效應。
- **map**：Flow運算符，用於轉換Flow中每個發射的值。
- **flatMapConcat**：Flow運算符，用於將每個值轉換為另一個Flow，並按順序串接這些Flow。
- **flatMapMerge**：Flow運算符，用於同時收集多個Flow的值，並發射它們的結果。
- **flatMapLatest**：Flow運算符，類似於flatMapConcat，但僅保留最新的Flow結果。
- **collectLatest**：Flow運算符，僅處理最新的數據發射，忽略之前未完成的發射。
- **buffer**：Flow運算符，用於在不同的協程中處理Flow的發射和收集，提升性能。
- **conflate**：Flow運算符，丟棄未處理的舊值，只保留最新的發射。
- **launchIn**：將Flow在指定的協程範圍內啟動並收集的方法。
- **ViewModel**：Android中的架構組件，用於保存和管理UI相關數據。
- **ViewModelScope**：一個用於ViewModel中的協程範圍，確保協程在ViewModel清理時被取消。
- **repeatOnLifecycle**：一個用於在指定生命週期狀態下啟動和停止協程的函數。
- **DispatcherProvider**：用於提供協程調度器的接口，允許在應用中注入不同的協程調度器。
- **Main Dispatcher**：Kotlin協程的主線程調度器，通常用於UI相關的協程調度。
- **TestCoroutineDispatcher**：專門用於測試的協程調度器，允許控制協程的時間進程。
- **runBlocking**：Kotlin中的阻塞式協程運行器，用於測試中以同步方式運行協程。
- **runBlockingTest**：專門用於測試協程的運行器，允許控制協程的時間流逝。
- **CoroutineScope**：協程的範圍，控制協程的生命周期和上下文。
- **Job**：Kotlin協程中的工作單元，表示協程的生命周期，可用於控制協程的執行。
- **launch**：Kotlin中的協程構建器，用於啟動新的協程。
- **async**：Kotlin中的協程構建器，用於啟動新的協程並返回其結果的Deferred對象。
- **Deferred**：Kotlin中的協程結果的延遲對象，允許協程異步返回結果。
- **suspend function**：Kotlin中的掛起函數，允許非同步運行代碼。
- **LiveData**：Android中的可觀察數據持有類型，具有生命週期感知能力。
- **Turbine**：一個用於簡化Flow單元測試的庫，提供方法來等待並驗證Flow的發射結果。
- **Truth**：Google提供的一個斷言庫，用於替代傳統JUnit斷言，提供更可讀的測試斷言。
- **Kotlin Coroutine**：Kotlin中的非同步和並發編程工具，用於簡化異步操作。
- **Channel**：Kotlin中的通信管道，用於在協程之間傳遞數據。
- **Cold Flow**：只有在被收集時才會啟動的Flow。
- **Hot Flow**：無論有無觀察者，Flow都會持續運作和發射數據。
- **State**：Jetpack Compose中的狀態容器，允許UI在狀態變化時重組。
- **MutableState**：Compose中的可變狀態容器，允許在UI中動態更新狀態。
- **LaunchedEffect**：Compose中的一個效應，用於在組合外部啟動協程。
- **remember**：Compose中的記憶工具，允許在重組過程中保留狀態或對象。
- **snapshotFlow**：Compose中的一個擴展函數，將可組合狀態轉換為Flow。
- **collectAsState**：將Flow轉換為Compose中可觀察的狀態，適合在UI中使用Flow。
