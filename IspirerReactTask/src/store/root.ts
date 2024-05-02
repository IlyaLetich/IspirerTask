import { NewsStore } from "./news-store";
import { StoriesStore } from "./stories-store";

export class RootStore {
  public storiesStore: StoriesStore;
  public newsStore: NewsStore;

  constructor(
    storiesStore: StoriesStore,
    newsStore: NewsStore
  ) {
    this.storiesStore = storiesStore;
    this.newsStore = newsStore;
  }
}

export const store = new RootStore(
  new StoriesStore(),
  new NewsStore()
);
